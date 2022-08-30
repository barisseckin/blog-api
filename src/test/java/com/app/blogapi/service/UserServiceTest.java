package com.app.blogapi.service;


import com.app.blogapi.TestUtils;
import com.app.blogapi.dto.UserDto;
import com.app.blogapi.dto.converter.UserDtoConverter;
import com.app.blogapi.dto.request.CreateUserRequest;
import com.app.blogapi.exception.NotFoundException;
import com.app.blogapi.model.User;
import com.app.blogapi.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest extends TestUtils {

    private UserRepository userRepository;
    private UserDtoConverter userDtoConverter;
    private UserService userService;

    @BeforeEach
    public void setUp() {
        userRepository = mock(UserRepository.class);
        userDtoConverter = mock(UserDtoConverter.class);

        userService = new UserService(userRepository, userDtoConverter);
    }

    @Test
    public void testGetAllUser_itShouldReturnUserDtoList() {
        List<User> userList = generateUserList();
        List<UserDto> userDtoList = generateUserDtoList(userList);

        when(userRepository.findAll()).thenReturn(userList);
        when(userDtoConverter.convert(userList)).thenReturn(userDtoList);

        List<UserDto> result = userService.getAll();

        assertEquals(userDtoList, result);
        verify(userRepository).findAll();
        verify(userDtoConverter).convert(userList);
    }

    @Test
    public void testGetUserByMail_itShouldReturnUser() {
        LocalDate createDate = LocalDate.now();
        LocalDate updateDate = LocalDate.now();
        User user = generateUser(createDate, updateDate);

        when(userRepository.findUserByMail(user.getMail())).thenReturn(Optional.of(user));

        User result = userService.getUserByMail(user.getMail());

        assertEquals(user, result);
        verify(userRepository).findUserByMail(user.getMail());
    }

    @Test
    public void testGetByUserName_itShouldReturnUserDto() {
        LocalDate createDate = LocalDate.now();
        LocalDate updateDate = LocalDate.now();
        User user = generateUser(createDate, updateDate);
        UserDto userDto = generateUserDto(user);

        when(userRepository.findUserByUserName(user.getUserName())).thenReturn(Optional.of(user));
        when(userDtoConverter.convert(user)).thenReturn(userDto);

        UserDto result = userService.getByUserName(user.getUserName());

        assertEquals(userDto, result);
        verify(userRepository).findUserByUserName(user.getUserName());
        verify(userDtoConverter).convert(user);
    }

    @Test
    public void testGetByMail_itShouldReturnUserDto() {
        LocalDate createDate = LocalDate.now();
        LocalDate updateDate = LocalDate.now();
        User user = generateUser(createDate, updateDate);
        UserDto userDto = generateUserDto(user);

        when(userRepository.findUserByMail(user.getMail())).thenReturn(Optional.of(user));
        when(userDtoConverter.convert(user)).thenReturn(userDto);

        UserDto result = userService.getByMail(user.getMail());

        assertEquals(userDto, result);
        verify(userRepository).findUserByMail(user.getMail());
        verify(userDtoConverter).convert(user);
    }

    @Test
    public void testSave_itShouldReturnUserDto() {
        CreateUserRequest request = new CreateUserRequest();
        request.setUserName("username");
        request.setPassword("password");
        request.setMail("test@gmail.com");

        LocalDate createDate = LocalDate.now();
        LocalDate updateDate = LocalDate.now();
        User user= new User("username", "test@gmail.com", "password", createDate);
        User savedUser = generateUser(createDate, updateDate);
        UserDto userDto = generateUserDto(user);

        when(userRepository.save(user)).thenReturn(savedUser);
        when(userDtoConverter.convert(savedUser)).thenReturn(userDto);

        UserDto result = userService.save(request);

        assertEquals(userDto, result);
        verify(userRepository).save(user);
        verify(userDtoConverter).convert(savedUser);
    }

    @Test
    public void testGetUserByMail_whenUserMailDoesNotExist_shouldReturnUserNotFoundException() {
        when(userRepository.findUserByMail("test@gmail.com")).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> userService.getUserByMail("test@gmail.com"));

        verify(userRepository).findUserByMail("test@gmail.com");
    }


}