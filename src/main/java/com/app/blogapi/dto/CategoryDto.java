package com.app.blogapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class CategoryDto {
    private String name;
    private LocalDate createDate;
}
