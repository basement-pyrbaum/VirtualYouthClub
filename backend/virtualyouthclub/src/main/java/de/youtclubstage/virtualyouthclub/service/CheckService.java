package de.youtclubstage.virtualyouthclub.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CheckService {

    public static final String USER_ROLE = "user";
    public static final String ADMIN_ROLE = "admin";

    private final AgreementService agreementService;
    private final StateService stateService;

    public CheckService(AgreementService agreementService,
                        StateService stateService){
        this.agreementService = agreementService;
        this.stateService = stateService;
    }

    public boolean isValidUserAndOpenOrAdmin(){
        return (hasRole(USER_ROLE) && agreementService.hasAgreement() && stateService.isOpen())|| hasRole(ADMIN_ROLE);
    }

    public boolean isAdmin(){
        return hasRole(ADMIN_ROLE);
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



}
