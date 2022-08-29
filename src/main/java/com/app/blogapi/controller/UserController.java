package com.app.blogapi.controller;

import com.app.blogapi.dto.UserDto;
import com.app.blogapi.dto.request.CreateUserRequest;
import com.app.blogapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserDto> save(@RequestBody @Valid CreateUserRequest request) {
        return new ResponseEntity<>(userService.save(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAll() {
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }

    @DeleteMapping("/{mail}")
    public ResponseEntity<?> deleteByMail(@PathVariable("mail") String mail) {
        userService.deleteByMail(mail);
        return new ResponseEntity<>("user deleted", HttpStatus.OK);
    }

    @GetMapping("/get-by-mail/{mail}")
    public ResponseEntity<UserDto> getByMail(@PathVariable("mail") String mail) {
        return new ResponseEntity<>(userService.getByMail(mail), HttpStatus.OK);
    }

    @GetMapping("/get-by-userName/{userName}")
    public ResponseEntity<UserDto> getByUserName(@PathVariable("userName") String userName) {
        return new ResponseEntity<>(userService.getByUserName(userName), HttpStatus.OK);
    }

    @PatchMapping("/deactivate-user/{mail}")
    public ResponseEntity<UserDto> deactivateUser(@PathVariable("mail") String mail) {
        return new ResponseEntity<>(userService.deactivateUser(mail), HttpStatus.OK);
    }

    @PatchMapping("/activate-user/{mail}")
    public ResponseEntity<UserDto> activateUser(@PathVariable("mail") String mail) {
        return new ResponseEntity<>(userService.activateUser(mail), HttpStatus.OK);
    }
}
