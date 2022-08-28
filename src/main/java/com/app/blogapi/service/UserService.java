package com.app.blogapi.service;

import com.app.blogapi.dto.UserDto;
import com.app.blogapi.dto.converter.UserDtoConverter;
import com.app.blogapi.dto.request.CreateUserRequest;
import com.app.blogapi.exception.NotFoundException;
import com.app.blogapi.model.User;
import com.app.blogapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final UserDtoConverter userDtoConverter;

    public UserDto save(CreateUserRequest request) {
        User user = new User(
                request.getUserName(),
                request.getMail(),
                request.getPassword(),
                LocalDate.now()
        );

        log.info("user created, mail: " + user.getMail());
        return userDtoConverter.convert(userRepository.save(user));
    }

    public List<UserDto> getAll() {
        return userDtoConverter.convert(userRepository.findAll());
    }

    public UserDto getByMail(String mail) {
        return userDtoConverter.convert(getUserByMail(mail));
    }

    public UserDto getByUserName(String userName) {
        return userDtoConverter.convert(userRepository.findUserByUserName(userName)
                .orElseThrow(() -> new NotFoundException("user not found, user name: " + userName)));
    }

    public void deleteByMail(String mail) {
        User user = getUserByMail(mail);
        log.info("user deleted, mail: " + user.getMail());
        userRepository.deleteById(user.getId());
    }

    public UserDto deactivateUser(String mail) {
        User user = getUserByMail(mail);

        user.setActive(false);
        return userDtoConverter.convert(userRepository.save(user));
    }

    public UserDto activateUser(String mail) {
        User user = getUserByMail(mail);

        user.setActive(true);
        return userDtoConverter.convert(userRepository.save(user));
    }

    protected User getUserByMail(String mail) {
        return userRepository.findUserByMail(mail)
                .orElseThrow(() -> new NotFoundException("user not found, mail: " + mail));
    }
}
