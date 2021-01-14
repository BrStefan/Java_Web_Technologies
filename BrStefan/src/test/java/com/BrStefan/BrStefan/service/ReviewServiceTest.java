package com.BrStefan.BrStefan.service;

import com.BrStefan.BrStefan.domain.Reservation;
import com.BrStefan.BrStefan.domain.Review;
import com.BrStefan.BrStefan.domain.User;
import com.BrStefan.BrStefan.domain.dto.*;
import com.BrStefan.BrStefan.repository.ReservationRepository;
import com.BrStefan.BrStefan.repository.ReviewRepository;
import com.BrStefan.BrStefan.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ReviewServiceTest {

    @InjectMocks
    private ReviewService reviewService;

    @Mock
    private ReviewRepository reviewRepository;


    @Test
    void testBook(){
        ReviewDTO reviewDTO = new ReviewDTO(1,1,4,"Test");
        Review review = new Review(1,1,1,4,"Test");

        reviewService.add(reviewDTO);

        assertThat(review.getReview()).isEqualTo(reviewDTO.getReview());

    }

}