package ru.dasha.wedding.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.dasha.wedding.domain.user.Role;
import ru.dasha.wedding.domain.user.User;
import ru.dasha.wedding.repos.UserRepo;
import ru.dasha.wedding.service.UserService;

import javax.transaction.Transactional;
import java.util.Collections;

@RestController
@RequestMapping("/api")
@PreAuthorize("hasAuthority('ADMIN')")
public class RegistrationRestController {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserService userService;

    @PostMapping("/addUser")
    public ResponseEntity<String> addUser(
           @RequestBody User user
    ){
        User userFromDb = userRepo.findByUsername(user.getUsername());
        if(userFromDb != null){
            return ResponseEntity.accepted().body("User exists");
        }
        else {
          userService.addUser(user);
            return ResponseEntity.accepted().body("User was created");
        }
    }

    @DeleteMapping("/deleteUser/{user}")
    @Transactional
    public ResponseEntity<String> deleteUser(
            @PathVariable User user
    ){
        try{
            System.out.println(user);
            user.getRoles().clear();
            userRepo.delete(user);
            return ResponseEntity.accepted().body("User "+user.getId()+" was deleted");
        }
        catch (Exception ex){
            return ResponseEntity.accepted().body("Error");
        }

    }
}
