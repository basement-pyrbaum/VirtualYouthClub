package de.youtclubstage.virtualyouthclub.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Slf4j
public class SecurityInterceptor implements HandlerInterceptor {

    private final SecurityService securityService;

    public SecurityInterceptor(SecurityService securityService) {
        this.securityService = securityService;
    }

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        HandlerMethod hm;
        try {
            hm = (HandlerMethod) handler;
            Method method = hm.getMethod();
            if (method.isAnnotationPresent(SecurityAnotation.class)) {
                return checkAdminByAnnotation(request,response, method);
            }
        } catch (ClassCastException e) {
            log.debug("error by preHandle",e);
        }
        return true;
    }

    private boolean checkAdminByAnnotation(HttpServletRequest request,HttpServletResponse response, Method method) {
        SecurityAnotation annotation = method.getAnnotation(SecurityAnotation.class);
        List<UserType> userTypes = Arrays.asList(annotation.adminType());
        if(annotation.openCheck()){
            if(annotation.orgType() == OrgType.GROUP) {
                Long groupId = getGroupId(request);
                if(groupId == null){
                    response.setStatus(403);
                    return false;
                }

              if(!securityService.isValidUserAndOpenOrAdminByGroup(groupId)){
                    response.setStatus(403);
                    return false;
              }
            }else if( !securityService.isValidUserAndOpenOrAdmin()){
                log.error("Room is closed, user has no permission");
                response.setStatus(403);
                return false;
            }
        }
        if(annotation.agreementCheck() &&
            !securityService.hasUserAgreement()){
            log.error("user has no agreement");
            response.setStatus(403);
            return false;
        }

        return checkAdmin(request,response,annotation, userTypes);
    }

    private Long getGroupId(HttpServletRequest request) {
        final Map<String, String> pathVariables = (Map<String, String>) request
                  .getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        String groupIdAsString = pathVariables.get("group-id");
        return groupIdAsString != null ? Long.valueOf(groupIdAsString):null;
    }

    private boolean checkAdmin(HttpServletRequest request,HttpServletResponse response,SecurityAnotation annotation , List<UserType> userTypes) {
        if(annotation.orgType() == OrgType.GROUP) {
            Long groupId = getGroupId(request);
            if (groupId == null) {
                response.setStatus(403);
                return false;
            }


        }


        boolean isGranted = false;
        if(userTypes.contains(UserType.USER)){
            if(securityService.isValidUser()){
                isGranted = true;
            }
            else{
                log.error("User is invalid");
            }

        }else{
            if(userTypes.contains(UserType.ADMIN)){
                if(securityService.isAdmin()){
                    isGranted = true;
                }
                else {
                    log.error("User is no valid Admin");
                }
            }
        }
        if(!isGranted){
            response.setStatus(403);
        }
        return  isGranted;
    }
}
