package com.app.blogapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class BlogDto {
    private String publicId;
    private String title;
    private String body;
    private int likeNumber;
    private int dislikeNumber;
    private LocalDate createDate;
    private LocalDate updateDate;
    private String categoryName;
    private String userMail;
}
