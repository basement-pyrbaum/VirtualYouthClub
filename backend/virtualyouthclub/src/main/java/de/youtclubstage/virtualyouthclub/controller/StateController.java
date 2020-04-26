package de.youtclubstage.virtualyouthclub.controller;

import de.youtclubstage.virtualyouthclub.controller.model.StateDto;
import de.youtclubstage.virtualyouthclub.security.SecurityAnotation;
import de.youtclubstage.virtualyouthclub.security.UserType;
import de.youtclubstage.virtualyouthclub.service.AgreementService;
import de.youtclubstage.virtualyouthclub.service.CheckService;
import de.youtclubstage.virtualyouthclub.service.StateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    @SecurityAnotation
    @RequestMapping(method = RequestMethod.GET, value={"/state","/public/state"},produces = "application/json")
    ResponseEntity<Boolean> isOpen(){
        return ResponseEntity.ok(stateService.isOpen());
    }


    @SecurityAnotation
    @RequestMapping(method = RequestMethod.GET, value="/extendedState",produces = "application/json")
    ResponseEntity<StateDto> isOpenOrAdmin(){
        return ResponseEntity.ok(new StateDto(checkService.getState()));
    }

    @SecurityAnotation
    @RequestMapping(method = RequestMethod.GET, value="/admin",produces = "application/json")
    ResponseEntity<Boolean> Admin(){
        return ResponseEntity.ok(checkService.isAdmin());
    }

    @SecurityAnotation
    @RequestMapping(method = RequestMethod.POST, value="/agreement")
    ResponseEntity<Void> setAgreement(){
        agreementService.createAgreement();
        return ResponseEntity.ok().build();
    }


    @SecurityAnotation( adminType = { UserType.ADMIN })
    @RequestMapping(method = RequestMethod.POST, value="/state")
    ResponseEntity<Void> isOpen(@RequestBody boolean open){
        stateService.setOpen(open);
        return ResponseEntity.ok().build();
    }

}
