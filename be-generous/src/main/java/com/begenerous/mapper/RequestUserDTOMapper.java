package com.begenerous.mapper;

import com.begenerous.DTO.RequestUserDTO;
import com.begenerous.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RequestUserDTOMapper implements Mapper<User, RequestUserDTO> {
    @Override
    public RequestUserDTO convertOne(User user) {
        return new RequestUserDTO(
                user.getUserId(),
                user.getEmail(),
                "",
                user.getFullName(),
                user.getAvatarURL()
        );
    }

    @Override
    public List<RequestUserDTO> convertList(List<User> users) {
        return users.stream().map(this::convertOne).collect(Collectors.toList());
    }
}
