package de.youtclubstage.virtualyouthclub.controller;

import de.youtclubstage.virtualyouthclub.controller.model.CreateComplaintDto;
import de.youtclubstage.virtualyouthclub.controller.model.MessageDTO;
import de.youtclubstage.virtualyouthclub.controller.model.MessageDetailDto;
import de.youtclubstage.virtualyouthclub.service.CheckService;
import de.youtclubstage.virtualyouthclub.service.ComplaintService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api")
public class ComplaintController {
    private final ComplaintService complaintService;
    private final CheckService checkService;

    @Autowired
    public ComplaintController(ComplaintService complaintService, CheckService checkService) {
        this.complaintService = complaintService;
        this.checkService = checkService;
    }

    @RequestMapping(method = RequestMethod.POST, value="/complaints")
    ResponseEntity<Void> createComplaint(@RequestBody CreateComplaintDto createComplaintDto){
        complaintService.createComplaint(createComplaintDto);
        return ResponseEntity.ok().build();
    }


    @RequestMapping(method = RequestMethod.GET, value="/complaints/unread", produces = "application/json")
    ResponseEntity<Long> getUnread(){
        if(!checkService.isAdmin()){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.ok(complaintService.getUnreadComplains());
    }

    @RequestMapping(method = RequestMethod.GET, value="/complaints", produces = "application/json")
    ResponseEntity<Page<MessageDTO>> getComplaints(Pageable pageable){
        if(!checkService.isAdmin()){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        Page<MessageDTO>  data = complaintService.getComplaint(pageable);
        return ResponseEntity.ok(data);
    }

    @RequestMapping(method = RequestMethod.GET, value="/complaints/{id}",produces = "application/json")
    ResponseEntity<MessageDetailDto> getComplaint(@PathVariable("id") UUID id){
        if(!checkService.isAdmin()){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        Optional<MessageDetailDto> data = complaintService.getComplaint(id);
        return data.map(messageDetailDto -> ResponseEntity.ok(messageDetailDto))
                .orElse(ResponseEntity.notFound().build());
    }

    @RequestMapping(method = RequestMethod.DELETE, value="/complaints/{id}")
    ResponseEntity<Void> deleteComplaint(@PathVariable("id") UUID id){
        if(!checkService.isAdmin()){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        if(complaintService.deleteComplaint(id)){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


}
