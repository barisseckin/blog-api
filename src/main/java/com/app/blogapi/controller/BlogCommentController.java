package com.app.blogapi.controller;

import com.app.blogapi.dto.BlogCommentDto;
import com.app.blogapi.dto.request.CreateBlogCommentRequest;
import com.app.blogapi.service.BlogCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/blog-comment")
@RequiredArgsConstructor
public class BlogCommentController {

    private final BlogCommentService blogCommentService;

    @PostMapping
    public ResponseEntity<BlogCommentDto> save(@RequestBody @Valid CreateBlogCommentRequest request) {
        return new ResponseEntity<>(blogCommentService.save(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<BlogCommentDto>> getAll() {
        return new ResponseEntity<>(blogCommentService.getAll(), HttpStatus.OK);
    }

    @DeleteMapping("/{body}")
    public ResponseEntity<?> deleteByBody(@PathVariable("body") String body) {
        blogCommentService.deleteByBody(body);
        return new ResponseEntity<>("deleted", HttpStatus.OK);
    }
}
