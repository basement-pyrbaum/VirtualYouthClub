package de.youtclubstage.virtualyouthclub.controller;

import de.youtclubstage.virtualyouthclub.entity.Room;
import de.youtclubstage.virtualyouthclub.security.OrgType;
import de.youtclubstage.virtualyouthclub.security.SecurityAnotation;
import de.youtclubstage.virtualyouthclub.security.SecurityService;
import de.youtclubstage.virtualyouthclub.security.UserType;
import de.youtclubstage.virtualyouthclub.service.RoomService;
import de.youtclubstage.virtualyouthclub.service.StateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Slf4j
@RestController
@RequestMapping("/api")
public class GroupRoomController {
    private final RoomService roomService;
    private final StateService stateService;
    private final SecurityService securityService;

    @Autowired
    public GroupRoomController(RoomService roomService,
                          StateService stateService,
                          SecurityService securityService){
        this.roomService = roomService;
        this.stateService = stateService;
        this.securityService = securityService;
    }


    @SecurityAnotation(openCheck = true, orgType = OrgType.GROUP)
    @RequestMapping(method = RequestMethod.GET, value = "/groups/{group-id}/rooms", produces = "application/json")
    ResponseEntity<List<Room>> getRooms(@PathVariable("group-id") UUID groupId,@RequestParam(value = "search", required = false) String search, Pageable page){
        Page<Room> rooms = roomService.getRoomsByGroup(groupId,search,page);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("totalItems",
                ""+rooms.getTotalElements());
        return ResponseEntity.ok().headers(responseHeaders).body(rooms.getContent());
    }

    @SecurityAnotation(openCheck = true, orgType = OrgType.GROUP)
    @RequestMapping(method = RequestMethod.GET, value = "/groups/{group-id}/rooms/{id}", produces = "application/json")
    ResponseEntity<Room> getRooms(@PathVariable("group-id") UUID groupId, @PathVariable(value = "id") UUID id){
        Optional<Room> room = roomService.getRoom(id);
        return room.map(value -> ResponseEntity.ok().body(value)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @SecurityAnotation(adminType = {UserType.ADMIN}, orgType = OrgType.GROUP)
    @RequestMapping(method = RequestMethod.DELETE, value = "/groups/{group-id}/rooms/{id}")
    ResponseEntity<Void> deleteRooms(@PathVariable("group-id") UUID groupId,@PathVariable(value = "id") UUID id){
        if(roomService.delete(groupId,id)) {
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @SecurityAnotation(adminType = {UserType.ADMIN}, orgType = OrgType.GROUP)
    @RequestMapping(method = RequestMethod.POST, value = "/groups/{group-id}/rooms")
    ResponseEntity<Void> addRoom(@PathVariable("group-id") UUID groupId,@RequestBody @Pattern(message = "NotAllowed", regexp = "[1-9A-Za-z_-]{1,20}") String name){
        roomService.addRoom(groupId,name);
        return ResponseEntity.ok().build();
    }

    @SecurityAnotation(orgType = OrgType.GROUP)
    @RequestMapping(method = RequestMethod.GET, value = "/groups/{group-id}/permission/add/rooms", produces = "application/json")
    ResponseEntity<Boolean> hasPermissionCreateRooms(@PathVariable("group-id") Long groupId){
        return ResponseEntity.ok(securityService.isAdmin());
    }

}
