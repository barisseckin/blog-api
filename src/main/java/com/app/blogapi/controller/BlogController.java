package com.app.blogapi.controller;

import com.app.blogapi.dto.BlogDto;
import com.app.blogapi.dto.request.CreateBlogRequest;
import com.app.blogapi.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/blog")
@RequiredArgsConstructor
public class BlogController {

    private final BlogService blogService;

    @PostMapping
    public ResponseEntity<BlogDto> save(@RequestBody @Valid CreateBlogRequest request) {
        return new ResponseEntity<>(blogService.save(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<BlogDto>> getAll() {
        return new ResponseEntity<>(blogService.getAll(), HttpStatus.OK);
    }
}
