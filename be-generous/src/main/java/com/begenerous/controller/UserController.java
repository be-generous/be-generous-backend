package com.begenerous.controller;

import com.begenerous.DTO.ResponseUserDTO;
import com.begenerous.DTO.RoleToUserDTO;
import com.begenerous.DTO.RequestUserDTO;
import com.begenerous.exception.DuplicatedEmailException;
import com.begenerous.mapper.RequestUserDTOMapper;
import com.begenerous.mapper.RequestUserEntityMapper;
import com.begenerous.mapper.ResponseUserDTOMapper;
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
    // TODO delete later if not needed anymore
    private final RequestUserDTOMapper requestUserDTOMapper;
    private final RequestUserEntityMapper requestuserEntityMapper;
    private final ResponseUserDTOMapper responseUserDTOMapper;

    @PostMapping(path = "/user")
    public ResponseEntity<?> saveUser(@Valid @RequestBody RequestUserDTO userDTO) {
        try {
            // TODO return specific message
            return new ResponseEntity<>(userService.saveUser(requestuserEntityMapper.convertOne(userDTO)), HttpStatus.OK);
        } catch (DuplicatedEmailException e) {
            return ExceptionHandlerUtils.invalidInputException("There is already a user registered with this email address!");
        } catch (Exception e) {
            return ExceptionHandlerUtils.invalidInputException("There user couldn't be saved!");
        }
    }

    @PostMapping(path = "/user/update")
    public ResponseEntity<?> updateUser(@Valid @RequestBody RequestUserDTO requestUserDTO) {
        // TODO return specific message
        return new ResponseEntity<>(userService.updateUser(requestuserEntityMapper.convertOne(requestUserDTO)), HttpStatus.OK);
    }

    @PostMapping(path = "/user/addrole")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserDTO roleToUserDTO) {
        try {
            // TODO return specific message
            return new ResponseEntity<>(userService.addRoleToUser(roleToUserDTO.getUserId(), roleToUserDTO.getRoleId()), HttpStatus.OK);
        }
        catch (Exception e) {
            return ExceptionHandlerUtils.invalidInputException("Couldn't add role to user!");
        }
    }

    @GetMapping(path = "/user/{userId}")
    public ResponseEntity<?> getUser(@PathVariable("userId") Long userId) {
        try {
            return new ResponseEntity<>(responseUserDTOMapper.convertOne(userService.getUser(userId)), HttpStatus.OK);
        }
        catch (Exception e) {
            return ExceptionHandlerUtils.invalidInputException("No user with the id: " + userId);
        }
    }

    @PostMapping(path = "/role/")
    public ResponseEntity<?> saveRole(@RequestBody Role role) {
        // TODO return specific message
        return new ResponseEntity<>(userService.saveRole(role), HttpStatus.OK);
    }

}
