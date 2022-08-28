package com.app.blogapi.service;

import com.app.blogapi.dto.CategoryDto;
import com.app.blogapi.dto.converter.CategoryDtoConverter;
import com.app.blogapi.dto.request.CreateCategoryRequest;
import com.app.blogapi.exception.NotFoundException;
import com.app.blogapi.model.Category;
import com.app.blogapi.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryDtoConverter categoryDtoConverter;

    public CategoryDto save(CreateCategoryRequest request) {
        Category category = new Category(
                request.getName(),
                LocalDate.now()
        );
        log.info("category created, name: " + category.getName());
        return categoryDtoConverter.convert(categoryRepository.save(category));
    }

    public List<CategoryDto> getAll() {
        return categoryDtoConverter.convert(categoryRepository.findAll());
    }

    public CategoryDto getByName(String name) {
        return categoryDtoConverter.convert(getCategoryByName(name));
    }

    public void deleteByName(String name) {
        Category category = getCategoryByName(name);
        categoryRepository.deleteById(category.getId());
        log.info("category deleted, name: " + name);
    }

    protected Category getCategoryByName(String name) {
        return categoryRepository.findCategoryByName(name)
                .orElseThrow(() -> new NotFoundException("category not found, category name: " + name));
    }

}
