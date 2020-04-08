package de.youtclubstage.virtualyouthclub.controller;

import de.youtclubstage.virtualyouthclub.entity.Room;
import de.youtclubstage.virtualyouthclub.service.CheckService;
import de.youtclubstage.virtualyouthclub.service.RoomService;
import de.youtclubstage.virtualyouthclub.service.StateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;


import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api")
public class RoomController {

    private final RoomService roomService;
    private final StateService stateService;
    private final CheckService checkService;

    @Autowired
    public RoomController(RoomService roomService,
                          StateService stateService,
                          CheckService checkService){
        this.roomService = roomService;
        this.stateService = stateService;
        this.checkService = checkService;
    }



    @RequestMapping(method = RequestMethod.GET, value = "/rooms", produces = "application/json")
    ResponseEntity<List<Room>> getRooms(@RequestParam(value = "search", required = false) String search, Pageable page){
        if(!checkService.isValidUserAndOpenOrAdmin()){
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
        if(!checkService.isValidUserAndOpenOrAdmin()){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        Optional<Room> room = roomService.getRoom(id);
        if(room.isPresent()) {
            return ResponseEntity.ok().body(room.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }


    @RequestMapping(method = RequestMethod.DELETE, value = "/rooms/{id}")
    ResponseEntity<Void> deleteRooms(@PathVariable(value = "id") UUID id){
        if(!checkService.isAdmin()){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        if(roomService.delete(id)) {
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }


    @RequestMapping(method = RequestMethod.POST, value = "/rooms")
    ResponseEntity<Void> addRoom(@RequestBody @Pattern(message = "NotAllowed", regexp = "[1-9A-Za-z_-]{1,20}") String name){
        if(!checkService.isAdmin()){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        roomService.addRoom(name);
        return ResponseEntity.ok().build();
        }


    @RequestMapping(method = RequestMethod.GET, value = "/permission/add/rooms", produces = "application/json")
    ResponseEntity<Boolean> hasPermissionCreateRooms(){
        return ResponseEntity.ok(checkService.isAdmin());
    }



}
