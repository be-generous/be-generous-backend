package com.begenerous.mapper;

import com.begenerous.DTO.RequestUserDTO;
import com.begenerous.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RequestUserEntityMapper implements Mapper<RequestUserDTO, User> {
    @Override
    public User convertOne(RequestUserDTO userDTO) {
        return new User(
                userDTO.getUserId(),
                userDTO.getEmail(),
                userDTO.getPassword(),
                userDTO.getFullName(),
                userDTO.getAvatarURL()
        );
    }

    @Override
    public List<User> convertList(List<RequestUserDTO> userDTOs) {
        return userDTOs.stream().map(this::convertOne).collect(Collectors.toList());
    }
}
