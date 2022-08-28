package com.app.blogapi.dto.converter;

import com.app.blogapi.dto.CategoryDto;
import com.app.blogapi.model.Category;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryDtoConverter {

    public CategoryDto convert(Category category) {
        return new CategoryDto(
                category.getName(),
                category.getCreateDate()
        );
    }

    public List<CategoryDto> convert(List<Category> categories) {
        return categories.stream().map(this::convert).collect(Collectors.toList());
    }
}
