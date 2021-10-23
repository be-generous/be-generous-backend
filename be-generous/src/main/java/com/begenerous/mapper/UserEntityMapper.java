package com.begenerous.mapper;

import com.begenerous.DTO.UserDTO;
import com.begenerous.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserEntityMapper implements Mapper<UserDTO, User> {
    @Override
    public User convertOne(UserDTO userDTO) {
        // TODO
        return new User();
    }

    @Override
    public List<User> convertList(List<UserDTO> userDTOs) {
        return userDTOs.stream().map(this::convertOne).collect(Collectors.toList());
    }
}
