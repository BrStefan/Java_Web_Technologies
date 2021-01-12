package com.BrStefan.BrStefan.service;

import com.BrStefan.BrStefan.domain.dto.ReviewDTO;
import com.BrStefan.BrStefan.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public void add(ReviewDTO reviewDTO){
        reviewRepository.add(reviewDTO);
    }


}
