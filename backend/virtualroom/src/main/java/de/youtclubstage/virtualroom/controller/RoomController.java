package de.youtclubstage.virtualroom.controller;

import de.youtclubstage.virtualroom.entity.Room;
import de.youtclubstage.virtualroom.service.RoomService;
import de.youtclubstage.virtualroom.service.StateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;


import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RestController
public class RoomController {

    public static final String USER_ROLE = "user";
    public static final String ADMIN_ROLE = "admin";
    private final RoomService roomService;
    private final StateService stateService;

    @Autowired
    public RoomController(RoomService roomService,
                          StateService stateService){
        this.roomService = roomService;
        this.stateService = stateService;
    }



    @RequestMapping(method = RequestMethod.GET, value = "/rooms", produces = "application/json")
    ResponseEntity<List<Room>> getRooms(@RequestParam(value = "search", required = false) String search, Pageable page){
        if(!isOpenAndUserOrAdmin()){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        log.error(SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString());

        Page<Room> rooms = roomService.getRooms(search,page);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("totalItems",
                ""+rooms.getTotalElements());
        return ResponseEntity.ok().headers(responseHeaders).body(rooms.getContent());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/rooms/{id}", produces = "application/json")
    ResponseEntity<Room> getRooms(@PathVariable(value = "id") UUID id){
        if(!isOpenAndUserOrAdmin()){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        Optional<Room> room = roomService.getRoom(id);
        if(room.isPresent()) {
            return ResponseEntity.ok().body(room.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/rooms")
    ResponseEntity<Void> addRoom(@RequestBody @Pattern(message = "NotAllowed", regexp = "[1-9A-Za-z_-]{1,20}") String name){
        if(!hasAddRoomPermission()){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        roomService.addRoom(name);
        return ResponseEntity.ok().build();
        }


    @RequestMapping(method = RequestMethod.GET, value = "/permission/add/rooms", produces = "application/json")
    ResponseEntity<Boolean> hasPermissionCreateRooms(){
        return ResponseEntity.ok(hasAddRoomPermission());
    }

    private boolean hasAddRoomPermission() {
        return hasRole(ADMIN_ROLE);
    }


    private boolean isOpenAndUserOrAdmin(){
       return (hasRole(USER_ROLE) && stateService.isOpen())|| hasRole(ADMIN_ROLE);
    }

    public boolean hasRole(String role){
        Jwt jwt = (Jwt)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
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
