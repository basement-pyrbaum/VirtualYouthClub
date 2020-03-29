package de.youtclubstage.virtualroom.controller;

import de.youtclubstage.virtualroom.service.StateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class StateController {
    private final StateService stateService;

    public static final String USER_ROLE = "user";
    public static final String ADMIN_ROLE = "admin";

    public StateController(StateService stateService){
        this.stateService = stateService;
    }

    @RequestMapping(method = RequestMethod.GET, value="/state",produces = "application/json")
    ResponseEntity<Boolean> isOpen(){
        return ResponseEntity.ok(stateService.isOpen());
    }

    @RequestMapping(method = RequestMethod.GET, value="/stateoradmin",produces = "application/json")
    ResponseEntity<Boolean> isOpenOrAdmin(){
        return ResponseEntity.ok(stateService.isOpen()||hasRole(ADMIN_ROLE));
    }

    @RequestMapping(method = RequestMethod.GET, value="/admin",produces = "application/json")
    ResponseEntity<Boolean> Admin(){
        return ResponseEntity.ok(hasRole(ADMIN_ROLE));
    }


    @RequestMapping(method = RequestMethod.POST, value="/state")
    ResponseEntity<Void> isOpen(@RequestBody boolean open){
        if(!hasRole(ADMIN_ROLE)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        stateService.setOpen(open);
        return ResponseEntity.ok().build();
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
