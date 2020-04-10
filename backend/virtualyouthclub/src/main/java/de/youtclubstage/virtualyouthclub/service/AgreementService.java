package de.youtclubstage.virtualyouthclub.service;

import de.youtclubstage.virtualyouthclub.entity.Agreement;
import de.youtclubstage.virtualyouthclub.repository.AgreementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.UUID;

@Service
public class AgreementService {

    private final AgreementRepository agreementRepository;

    @Autowired
    public AgreementService(AgreementRepository agreementRepository){
        this.agreementRepository = agreementRepository;
    }

    public boolean hasAgreement(){
        return agreementRepository.findById(getUserId()).isPresent();
    }

    public void createAgreement(){
        agreementRepository.save(new Agreement(getUserId(), Calendar.getInstance().getTime()));
    }

    private UUID getUserId(){
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return UUID.fromString(jwt.getClaimAsString("sub"));
    }


}
