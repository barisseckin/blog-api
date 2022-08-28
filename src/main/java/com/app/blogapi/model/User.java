package com.app.blogapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String userName;
    private String mail;
    private String password;
    private boolean isActive = true;
    private LocalDate createDate;
    private LocalDate updateDate;

    public User(String userName, String mail, String password, LocalDate createDate) {
        this.userName = userName;
        this.mail = mail;
        this.password = password;
        this.createDate = createDate;
    }
}
