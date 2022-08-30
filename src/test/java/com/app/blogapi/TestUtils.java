package com.app.blogapi;

import com.app.blogapi.dto.UserDto;
import com.app.blogapi.model.User;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestUtils {

    public List<User> generateUserList() {
        return IntStream.range(0, 5).mapToObj(i ->
                new User(i, "username", "test@gmail.com", "password", true, LocalDate.now(), LocalDate.now()))
                .collect(Collectors.toList());
    }

    public List<UserDto> generateUserDtoList(List<User> users) {
        return users.stream().map(user -> new UserDto(user.getUserName(), user.getMail(), user.isActive(), user.getCreateDate(), user.getUpdateDate()))
                .collect(Collectors.toList());
    }

    public User generateUser(LocalDate createDate, LocalDate updateDate) {
        return new User(1, "username", "test@gmail.com", "password", true, createDate, updateDate);
    }

    public UserDto generateUserDto(User user) {
        return new UserDto(user.getUserName(), user.getMail(), user.isActive(), user.getCreateDate(), user.getUpdateDate());
    }
}
