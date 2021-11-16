package com.begenerous.mapper;

import com.begenerous.DTO.RequestUserDTO;
import com.begenerous.DTO.ResponseUserDTO;
import com.begenerous.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ResponseUserDTOMapper implements Mapper<User, ResponseUserDTO> {
    @Override
    public ResponseUserDTO convertOne(User user) {
        return new ResponseUserDTO(
                user.getUserId(),
                user.getEmail(),
                user.getFullName(),
                user.getAvatarURL()
        );
    }

    @Override
    public List<ResponseUserDTO> convertList(List<User> users) {
        return users.stream().map(this::convertOne).collect(Collectors.toList());
    }
}

