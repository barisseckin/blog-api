package com.app.blogapi.repository;

import com.app.blogapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findUserByMail(String mail);
    Optional<User> findUserByUserName(String userName);
}
