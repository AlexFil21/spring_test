package com.family.family.controllers;

import com.family.family.dto.UserDTO;
import com.family.family.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/users")
    public ResponseEntity<List<UserDTO>> getFamilyMembers(){
        List<UserDTO> users = userService.users();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @DeleteMapping(value = "/user/{id}")
    public ResponseEntity<Void> deleteFamilyMember(@PathVariable Long id){
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/user")
    public ResponseEntity<UserDTO> addFamilyMember(@RequestBody UserDTO userDTO) {
        UserDTO dto = userService.addOrUpdate(userDTO);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/user")
    public ResponseEntity<UserDTO>  updateFamilyMember(@RequestBody UserDTO userDTO) {
        UserDTO dto = userService.addOrUpdate(userDTO);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }


    @GetMapping("/filter")
    public ResponseEntity<List<UserDTO>> filterFamilyMember(@RequestParam Integer a, @RequestParam Integer b){
        List<UserDTO> filter = userService.filter(a,b);
        return new ResponseEntity<>(filter, HttpStatus.OK);
    }
}
