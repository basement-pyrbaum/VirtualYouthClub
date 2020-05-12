package de.youtclubstage.virtualyouthclub.controller;


import de.youtclubstage.virtualyouthclub.controller.model.CreateGroup;
import de.youtclubstage.virtualyouthclub.controller.model.GroupDto;
import de.youtclubstage.virtualyouthclub.entity.Group;
import de.youtclubstage.virtualyouthclub.security.SecurityAnotation;
import de.youtclubstage.virtualyouthclub.security.UserType;
import de.youtclubstage.virtualyouthclub.service.GroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
public class GroupController {

    private final GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @SecurityAnotation
    @RequestMapping(method = RequestMethod.GET, value = "/groups", produces = "application/json")
    ResponseEntity<List<GroupDto>> getGroups(){
        return ResponseEntity.ok(groupService.getGroups());
    }

    @SecurityAnotation( adminType = { UserType.ADMIN })
    @RequestMapping(method = RequestMethod.POST, value="/groups")
    ResponseEntity<Void> createGroup(@RequestBody CreateGroup createGroup){
        groupService.add(createGroup);
        return ResponseEntity.ok().build();
    }


}
