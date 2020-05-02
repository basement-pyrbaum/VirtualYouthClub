package de.youtclubstage.virtualyouthclub.service;

import de.youtclubstage.virtualyouthclub.controller.model.CreateGroup;
import de.youtclubstage.virtualyouthclub.controller.model.GroupDto;
import de.youtclubstage.virtualyouthclub.entity.Group;
import de.youtclubstage.virtualyouthclub.repository.GroupRepository;
import de.youtclubstage.virtualyouthclub.security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class GroupService {

    private final GroupRepository groupRepository;
    private final SecurityService securityService;

    @Autowired
    public GroupService(GroupRepository groupRepository, SecurityService securityService) {
        this.groupRepository = groupRepository;
        this.securityService = securityService;
    }

    public List<GroupDto> getGroups(){
        List<Group> groups = new ArrayList<>();
        if(securityService.isAdmin()){
            groups = groupRepository.findAll();
        }else{
            //groups = TODO;
        }
        return groups.stream().map(Group::toGroupDto).collect(Collectors.toList());
    }

    public void add(CreateGroup group){
        groupRepository.save(new Group(UUID.randomUUID(),group.getName(),
                group.getAdmins(), new ArrayList<>(),false));
    }
}
