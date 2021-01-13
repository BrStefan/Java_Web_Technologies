package com.BrStefan.BrStefan.repository;

import com.BrStefan.BrStefan.domain.Group;
import com.BrStefan.BrStefan.domain.Reservation;
import com.BrStefan.BrStefan.domain.Review;
import com.BrStefan.BrStefan.domain.dto.ReviewDTO;
import com.BrStefan.BrStefan.exceptions.ReviewException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReviewRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void add(ReviewDTO reviewDTO){
        String sql = "SELECT * FROM reservations WHERE Id = ?";
        RowMapper<Reservation> rowMapper = (resultSet, rowNo) -> Reservation.builder()
                .owner(resultSet.getString("owner"))
                .build();
        List<Reservation> reservations = jdbcTemplate.query(sql, rowMapper, reviewDTO.getReservation_id());
        if(reservations.size()==0 || !reservations.get(0).getOwner().equals(String.valueOf(reviewDTO.getUser_id())))
            throw new ReviewException("This reservation does not belongs to you");
        else{
            sql = "INSERT INTO review(user_id, reservation_id, stars, review) VALUES (?,?,?,?)";
            jdbcTemplate.update(sql, reviewDTO.getUser_id(), reviewDTO.getReservation_id(), reviewDTO.getStars(), reviewDTO.getReview());
        }
    }

    private RowMapper<Review> buildReservation(){
        return (resultSet, rowNo) -> Review.builder()
                .id(resultSet.getInt("Id"))
                .user_id(resultSet.getInt("user_id"))
                .reservation_id(resultSet.getInt("reservation_id"))
                .stars(resultSet.getInt("stars"))
                .review(resultSet.getString("review"))
                .build();
    }
}
