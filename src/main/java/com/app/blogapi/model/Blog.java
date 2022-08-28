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
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String body;
    private int likeNumber;
    private int dislikeNumber;
    private LocalDate createDate;
    private LocalDate updateDate;
    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;
    @ManyToOne
    private User user;

    public Blog(String title, String body, LocalDate createDate, Category category, User user) {
        this.title = title;
        this.body = body;
        this.createDate = createDate;
        this.category = category;
        this.user = user;
    }
}
