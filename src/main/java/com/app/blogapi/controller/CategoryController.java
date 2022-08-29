package com.app.blogapi.controller;

import com.app.blogapi.dto.CategoryDto;
import com.app.blogapi.dto.request.CreateCategoryRequest;
import com.app.blogapi.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryDto> save(@RequestBody @Valid CreateCategoryRequest request) {
        return new ResponseEntity<>(categoryService.save(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAll() {
        return new ResponseEntity<>(categoryService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/get-by-name/{name}")
    public ResponseEntity<CategoryDto> getByName(@PathVariable("name") String name) {
        return new ResponseEntity<>(categoryService.getByName(name), HttpStatus.OK);
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<?> deleteByName(@PathVariable("name") String name) {
        categoryService.deleteByName(name);
        return new ResponseEntity<>("deleted", HttpStatus.OK);
    }
}
