package com.begenerous.controller;

import com.begenerous.DTO.RoleToUserDTO;
import com.begenerous.DTO.UserDTO;
import com.begenerous.exception.RowNotFoundException;
import com.begenerous.mapper.UserDTOMapper;
import com.begenerous.mapper.UserEntityMapper;
import com.begenerous.model.Role;
import com.begenerous.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserDTOMapper userDTOMapper;
    private final UserEntityMapper userEntityMapper;

    @PostMapping(path = "/user")
    public ResponseEntity<?> saveUser(@RequestBody UserDTO userDTO) {
        return new ResponseEntity<>(userService.saveUser(userEntityMapper.convertOne(userDTO)), HttpStatus.OK);
    }

    @PostMapping(path = "/user/addrole")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserDTO roleToUserDTO) throws RowNotFoundException {
        return new ResponseEntity<>(userService.addRoleToUser(roleToUserDTO.getUserId(), roleToUserDTO.getRoleId()), HttpStatus.OK);
    }

    @GetMapping(path = "/user/{userId}")
    public ResponseEntity<?> getUser(@PathVariable("userId") Long userId) throws RowNotFoundException {
        return new ResponseEntity<>(userDTOMapper.convertOne(userService.getUser(userId)), HttpStatus.OK);
    }

    @PostMapping(path = "/role/")
    public ResponseEntity<?> saveRole(@RequestBody Role role) {
        return new ResponseEntity<>(userService.saveRole(role), HttpStatus.OK);
    }

}
