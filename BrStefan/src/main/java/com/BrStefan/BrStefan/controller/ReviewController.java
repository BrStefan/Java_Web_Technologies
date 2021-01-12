package com.BrStefan.BrStefan.controller;

import com.BrStefan.BrStefan.domain.dto.ReviewDTO;
import com.BrStefan.BrStefan.exceptions.ReviewException;
import com.BrStefan.BrStefan.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequestMapping("/review")
@RestController
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping("/add")
    public ResponseEntity<Object> add(@RequestBody @Valid ReviewDTO reviewDTO){
        try{
            reviewService.add(reviewDTO);
            return ResponseEntity.ok().body("Review added!");
        }
        catch (ReviewException e){
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }
}
