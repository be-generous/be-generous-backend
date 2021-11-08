package com.begenerous.controller;

import com.begenerous.DTO.RoleToUserDTO;
import com.begenerous.DTO.UserDTO;
import com.begenerous.exception.DuplicatedEmailException;
import com.begenerous.exception.InvalidInputException;
import com.begenerous.exception.RowNotFoundException;
import com.begenerous.mapper.UserDTOMapper;
import com.begenerous.mapper.UserEntityMapper;
import com.begenerous.model.Role;
import com.begenerous.service.UserService;
import com.begenerous.util.ExceptionHandlerUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserDTOMapper userDTOMapper;
    private final UserEntityMapper userEntityMapper;

    @PostMapping(path = "/user")
    public ResponseEntity<?> saveUser(@Valid @RequestBody UserDTO userDTO) {
        try {
            return new ResponseEntity<>(userService.saveUser(userEntityMapper.convertOne(userDTO)), HttpStatus.OK);
        } catch (DuplicatedEmailException e) {
            return ExceptionHandlerUtils.invalidInputException("There is already a user registered with this email address!");
        } catch (Exception e) {
            return ExceptionHandlerUtils.invalidInputException("There user couldn't be saved!");
        }
    }

    @PostMapping(path = "/user/update")
    public ResponseEntity<?> updateUser(@Valid @RequestBody UserDTO userDTO) {
        return new ResponseEntity<>(userService.updateUser(userEntityMapper.convertOne(userDTO)), HttpStatus.OK);
    }

    @PostMapping(path = "/user/addrole")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserDTO roleToUserDTO) {
        try {
            return new ResponseEntity<>(userService.addRoleToUser(roleToUserDTO.getUserId(), roleToUserDTO.getRoleId()), HttpStatus.OK);
        }
        catch (Exception e) {
            return ExceptionHandlerUtils.invalidInputException("Couldn't add role to user!");
        }
    }

    @GetMapping(path = "/user/{userId}")
    public ResponseEntity<?> getUser(@PathVariable("userId") Long userId) {
        try {
            return new ResponseEntity<>(userDTOMapper.convertOne(userService.getUser(userId)), HttpStatus.OK);
        }
        catch (Exception e) {
            return ExceptionHandlerUtils.invalidInputException("No user with the id: " + userId);
        }
    }

    @PostMapping(path = "/role/")
    public ResponseEntity<?> saveRole(@RequestBody Role role) {
        return new ResponseEntity<>(userService.saveRole(role), HttpStatus.OK);
    }

}
