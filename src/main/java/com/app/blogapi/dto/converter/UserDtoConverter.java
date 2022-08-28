package com.app.blogapi.dto.converter;

import com.app.blogapi.dto.UserDto;
import com.app.blogapi.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDtoConverter {

    public UserDto convert(User user) {
        return new UserDto(
                user.getUserName(),
                user.getMail(),
                user.isActive(),
                user.getCreateDate(),
                user.getUpdateDate()
        );
    }

    public List<UserDto> convert(List<User> users) {
        return users.stream().map(this::convert).collect(Collectors.toList());
    }
}
