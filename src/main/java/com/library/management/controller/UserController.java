package com.library.management.controller;

import com.library.management.dto.UserDTO;
import com.library.management.dto.UserDataDTO;
import com.library.management.model.UserEntity;
import com.library.management.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserService userServ;

    @GetMapping("/get/{id}")
    public ResponseEntity<UserDataDTO> getUser(@PathVariable Long id) {
        try {
            UserDataDTO userFound = userServ.getUser(id);
            return new ResponseEntity<>(userFound, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        try {
            UserDTO newUser = userServ.createUser(userDTO);
            return new ResponseEntity<>(newUser, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

}
