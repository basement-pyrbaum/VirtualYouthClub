package de.youtclubstage.virtualyouthclub.controller;

import de.youtclubstage.virtualyouthclub.entity.Room;
import de.youtclubstage.virtualyouthclub.entity.User;
import de.youtclubstage.virtualyouthclub.security.SecurityAnotation;
import de.youtclubstage.virtualyouthclub.security.UserType;
import de.youtclubstage.virtualyouthclub.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @SecurityAnotation(adminType = {UserType.ADMIN})
    @RequestMapping(method = RequestMethod.GET, value = "/users", produces = "application/json")
    ResponseEntity<List<User>> getUsers(@RequestParam(value = "search", required = false) String search, Pageable page){
        Page<User> users = userService.getUsers(search,page);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("totalItems",
                ""+users.getTotalElements());
        return ResponseEntity.ok().headers(responseHeaders).body(users.getContent());
    }
}
