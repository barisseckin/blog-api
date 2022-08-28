package com.app.blogapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class BlogComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String body;
    private LocalDate createDate;
    private LocalDate updateDate;
    @ManyToOne
    private Blog blog;
    @ManyToOne
    private User user;

    public BlogComment(String body, LocalDate createDate, Blog blog, User user) {
        this.body = body;
        this.createDate = createDate;
        this.blog = blog;
        this.user = user;
    }
}
