package de.youtclubstage.virtualyouthclub.controller;

import de.youtclubstage.virtualyouthclub.controller.model.StateDto;
import de.youtclubstage.virtualyouthclub.entity.Agreement;
import de.youtclubstage.virtualyouthclub.service.AgreementService;
import de.youtclubstage.virtualyouthclub.service.CheckService;
import de.youtclubstage.virtualyouthclub.service.StateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
public class StateController {
    private final StateService stateService;
    private final CheckService checkService;
    private final AgreementService agreementService;

    public StateController(StateService stateService,
                           CheckService checkService,
                           AgreementService agreementService){
        this.stateService = stateService;
        this.checkService = checkService;
        this.agreementService = agreementService;
    }

    @RequestMapping(method = RequestMethod.GET, value={"/state","/public/state"},produces = "application/json")
    ResponseEntity<Boolean> isOpen(){
        return ResponseEntity.ok(stateService.isOpen());
    }


    @RequestMapping(method = RequestMethod.GET, value="/extendedState",produces = "application/json")
    ResponseEntity<StateDto> isOpenOrAdmin(){
        return ResponseEntity.ok(new StateDto(checkService.getState()));
    }

    @RequestMapping(method = RequestMethod.GET, value="/admin",produces = "application/json")
    ResponseEntity<Boolean> Admin(){
        return ResponseEntity.ok(checkService.isAdmin());
    }

    @RequestMapping(method = RequestMethod.POST, value="/agreement")
    ResponseEntity<Void> setAgreement(){
        agreementService.createAgreement();
        return ResponseEntity.ok().build();
    }


    @RequestMapping(method = RequestMethod.POST, value="/state")
    ResponseEntity<Void> isOpen(@RequestBody boolean open){
        if(!checkService.isAdmin()){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        stateService.setOpen(open);
        return ResponseEntity.ok().build();
    }

}
