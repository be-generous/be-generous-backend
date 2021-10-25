package com.begenerous.mapper;

import com.begenerous.DTO.UserDTO;
import com.begenerous.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDTOMapper implements Mapper<User, UserDTO> {
    @Override
    public UserDTO convertOne(User user) {
        return new UserDTO(
                user.getEmail(),
                "",
                user.getFullName(),
                user.getAvatarURL()
        );
    }

    @Override
    public List<UserDTO> convertList(List<User> users) {
        return users.stream().map(this::convertOne).collect(Collectors.toList());
    }
}
