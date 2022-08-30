package com.app.blogapi.controller;

import com.app.blogapi.dto.BlogDto;
import com.app.blogapi.dto.request.CreateBlogRequest;
import com.app.blogapi.dto.request.UpdateBlogRequest;
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

    @GetMapping("/get-by-category/{categoryName}")
    public ResponseEntity<List<BlogDto>> getBlogByCategory(@PathVariable("categoryName") String categoryName) {
        return new ResponseEntity<>(blogService.getBlogByCategory(categoryName), HttpStatus.OK);
    }

    @GetMapping("/search-by-title/{title}")
    public ResponseEntity<List<BlogDto>> searchBlogByTitle(@PathVariable("title") String title) {
        return new ResponseEntity<>(blogService.searchBlogByTitle(title), HttpStatus.OK);
    }

    @GetMapping("/get-by-publicId/{id}")
    public ResponseEntity<BlogDto> getByPublicId(@PathVariable("id") String id) {
        return new ResponseEntity<>(blogService.getByPublicId(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteByPublicId(@PathVariable("id") String id) {
        blogService.deleteByPublicId(id);
        return new ResponseEntity<>("deleted", HttpStatus.OK);
    }

    @PatchMapping("/increase-like-by-publicId/{id}")
    public ResponseEntity<BlogDto> increaseLikeByPublicId(@PathVariable("id") String id) {
        return new ResponseEntity<>(blogService.increaseLikeByPublicId(id), HttpStatus.OK);
    }

    @PatchMapping("/reduce-like-by-publicId/{id}")
    public ResponseEntity<BlogDto> reduceLikeByPublicId(@PathVariable("id") String id) {
        return new ResponseEntity<>(blogService.reduceLikeByPublicId(id), HttpStatus.OK);
    }

    @PatchMapping("/increase-dislike-by-publicId/{id}")
    public ResponseEntity<BlogDto> increaseDislikeByPublicId(@PathVariable("id") String id) {
        return new ResponseEntity<>(blogService.increaseDislikeByPublicId(id), HttpStatus.OK);
    }

    @PatchMapping("/reduce-dislike-by-publicId/{id}")
    public ResponseEntity<BlogDto> reduceDislikeNumberByPublicId(@PathVariable("id") String id) {
        return new ResponseEntity<>(blogService.reduceDislikeNumberByPublicId(id), HttpStatus.OK);
    }

    @PutMapping("/update-by-publicId/{id}")
    public ResponseEntity<BlogDto> update(@PathVariable("id") String publicId, @RequestBody UpdateBlogRequest request) {
        return new ResponseEntity<>(blogService.update(publicId, request), HttpStatus.OK);
    }
}
