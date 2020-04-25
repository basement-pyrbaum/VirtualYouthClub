package de.youtclubstage.virtualyouthclub.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
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
                return checkAdminByAnnotation(response, method);
            }
        } catch (ClassCastException e) {
            log.debug("error by preHandle",e);
        }
        return true;
    }

    private boolean checkAdminByAnnotation(HttpServletResponse response, Method method) {
        SecurityAnotation anotation = method.getAnnotation(SecurityAnotation.class);
        List<AdminType> adminTypes = Arrays.asList(anotation.adminType());
        if(anotation.openCheck()){
            //TODO: Gruppe muss nicht auf normales Open geprüft werden
            if(!securityService.isValidUserAndOpenOrAdmin()){
                response.setStatus(403);
                return false;
            }
        }
        return checkAdmin(response, adminTypes);
    }

    private boolean checkAdmin(HttpServletResponse response, List<AdminType> adminTypes) {
        boolean isGranted = false;
        if(adminTypes.contains(AdminType.USER)){
            isGranted = securityService.isValidUser();
        }else{
            if(adminTypes.contains(AdminType.GROUP_USER)){
                //TODO: Implementieren des GroupUserCheck
            }
            if(adminTypes.contains(AdminType.GROUP_ADMIN)){
                //TODO: Implementieren des GroupAdminCheck
            }
            if(adminTypes.contains(AdminType.ADMIN)){
                if(securityService.isAdmin()){
                    isGranted = true;
                }
            }
        }
        if(!isGranted){
            response.setStatus(403);
        }
        return  isGranted;
    }
}
