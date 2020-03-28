package de.youtclubstage.virtualroom.controller;

import de.youtclubstage.virtualroom.entity.Room;
import de.youtclubstage.virtualroom.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class RoomController {

    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService){
        this.roomService = roomService;
    }



    @RequestMapping(method = RequestMethod.GET, value = "/rooms", produces = "application/json")
    ResponseEntity<List<Room>> getRooms(@RequestParam(value = "search", required = false) String search, Pageable page){
        Page<Room> rooms = roomService.getRooms(search,page);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("totalItems",
                ""+rooms.getTotalElements());
        return ResponseEntity.ok().headers(responseHeaders).body(rooms.getContent());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/rooms/{id}", produces = "application/json")
    ResponseEntity<Room> getRooms(@PathVariable(value = "id") UUID id){
        Optional<Room> room = roomService.getRoom(id);
        if(room.isPresent()) {
            return ResponseEntity.ok().body(room.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
