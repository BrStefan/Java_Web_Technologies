package com.BrStefan.BrStefan.repository;

import com.BrStefan.BrStefan.domain.Group;
import com.BrStefan.BrStefan.domain.Notification;
import com.BrStefan.BrStefan.domain.User;
import com.BrStefan.BrStefan.domain.dto.NotificationDTO;
import com.BrStefan.BrStefan.exceptions.NotificationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.Not;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Repository
public class NotificationRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void add(NotificationDTO notificationDTO){
        String sql = "SELECT * FROM users WHERE users.Id = ?";
        RowMapper<User> rowMapper = (resultSet, rowNo) -> User.builder()
                .role(resultSet.getInt("Role"))
                .build();
        List<User> users = jdbcTemplate.query(sql, rowMapper, notificationDTO.getOwner());
        if(users.size() == 0 || users.get(0).getRole() != 2)
            throw new NotificationException("Not allowed for this id");
        else{
            sql = "SELECT * FROM groups WHERE groups.Id = ?";
            RowMapper<Group> rowMapper1 = (resultSet, rowNo) -> Group.builder()
                    .id(resultSet.getInt("Id"))
                    .build();
            List<Group> groups = jdbcTemplate.query(sql, rowMapper1, notificationDTO.getGroup());
            if (groups.size() == 0)
                throw new NotificationException("Group not found");
            else{
                sql = "INSERT INTO notifications (owner, notifications.group, message, date) VALUES (?,?,?,?)";
                jdbcTemplate.update(sql, notificationDTO.getOwner(), notificationDTO.getGroup(), notificationDTO.getMessage(), notificationDTO.getDate());
            }
        }
    }

    public List<Notification> getAll(){
        String sql = "SELECT * FROM notifications";
        RowMapper<Notification> rowMapper = (resultSet, rowNo) -> Notification.builder()
                .Id(resultSet.getInt("Id"))
                .owner(resultSet.getInt("owner"))
                .group(resultSet.getInt("group"))
                .message(resultSet.getString("message"))
                .date(resultSet.getDate("date"))
                .build();
        List<Notification> notifications = jdbcTemplate.query(sql, rowMapper);
        List<Notification> today = new ArrayList<>();
        for(Notification not : notifications){
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(not.getDate());
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            Calendar cal = Calendar.getInstance();
            int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);

            if (day == dayOfMonth){
                today.add(not);
            }
        }
        return today;
    }
}
