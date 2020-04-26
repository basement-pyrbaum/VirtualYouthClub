package de.youtclubstage.virtualyouthclub.security;

import de.youtclubstage.virtualyouthclub.service.AgreementService;
import de.youtclubstage.virtualyouthclub.service.CheckService;
import de.youtclubstage.virtualyouthclub.service.StateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class SecurityService {
    public static final String USER_ROLE = "user";
    public static final String ADMIN_ROLE = "admin";

    private final AgreementService agreementService;
    private final StateService stateService;

    private final CheckService checkService;

    @Autowired
    public SecurityService(AgreementService agreementService, StateService stateService, CheckService checkService) {
        this.agreementService = agreementService;
        this.stateService = stateService;
        this.checkService = checkService;
    }

    public boolean hasRole(String role){
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<String> roles = jwt.getClaimAsStringList("groups");
        if(roles != null && roles.contains(role)){
            return true;
        }
        if(roles != null){
            log.error(roles.toString());
        }
        return false;
    }

    public boolean isValidUserAndOpenOrAdmin(){
        return (hasRole(USER_ROLE) &&
                stateService.isOpen())|| hasRole(ADMIN_ROLE);
    }

    public boolean hasUserAgreement(){
        return  hasRole(ADMIN_ROLE) || agreementService.hasAgreement();
    }

    public boolean isAdmin(){
        return hasRole(ADMIN_ROLE);
    }

    public boolean isValidUser(){
        return hasRole(USER_ROLE) || hasRole(ADMIN_ROLE);
    }

    public String getState(){
        if(hasRole(ADMIN_ROLE)){
            return "ok";
        }
        if(hasRole(USER_ROLE)){
            if(!agreementService.hasAgreement()){
                return "need-agreement";
            }else if(!stateService.isOpen()){
                return "is-closed";
            }else{
                return "ok";
            }
        }
        return "not-a-user";
    }


    public boolean isValidUserAndOpenOrAdminByGroup(Long groupId) {
        return true;
    }
}
