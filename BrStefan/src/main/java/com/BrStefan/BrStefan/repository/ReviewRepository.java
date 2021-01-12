package com.BrStefan.BrStefan.repository;

import com.BrStefan.BrStefan.domain.Group;
import com.BrStefan.BrStefan.domain.Reservation;
import com.BrStefan.BrStefan.domain.dto.ReviewDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ReviewRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void add(ReviewDTO reviewDTO){
        String sql = "SELECT * FROM reservation WHERE Id = ?";
        RowMapper<Review> rowMapper = (resultSet, rowNo) -> Group.builder()
                .id(resultSet.getInt("Id"))
                .user_id(resultSet.getInt("owner"))
                .name(resultSet.getString("Name"))
                .build();
    }
}
