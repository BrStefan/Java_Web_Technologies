package com.BrStefan.BrStefan.repository;

import com.BrStefan.BrStefan.domain.Reservation;
import com.BrStefan.BrStefan.domain.User;
import com.BrStefan.BrStefan.domain.dto.RankUpDTO;
import com.BrStefan.BrStefan.domain.dto.UserLoginDTO;
import com.BrStefan.BrStefan.domain.dto.UserRegisterDTO;
import com.BrStefan.BrStefan.exceptions.LoginException;
import com.BrStefan.BrStefan.exceptions.UserExistsException;
import com.BrStefan.BrStefan.exceptions.UserRoleException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public User register(UserRegisterDTO user){

        User user_registered = getUser(user.getUsername());
        if (user_registered == null){
            String sql = "INSERT INTO users (Username, Password, Full_name, Role) VALUES (?, ?, ?, 1)";
            jdbcTemplate.update(sql, user.getUsername(), user.getPass(), user.getFull_name());
        }
        else throw new UserExistsException(user.getUsername());
        return getUser(user.getUsername());
    }

    public User login(UserLoginDTO user){
        String selectSql = "SELECT * from users WHERE users.Username = ? AND users.Password = ?";

        RowMapper<User> rowMapper = buildUser();
        String username = user.getUsername();
        String pass = user.getPass();
        List<User> user_queried = jdbcTemplate.query(selectSql, rowMapper, username, pass);
        if (user_queried.size() > 0){
            return user_queried.get(0);
        }
        else throw new LoginException();

    }

    public User getUser(String username) {
        String selectSql = "SELECT * from users WHERE users.Username = ?";

        RowMapper<User> rowMapper = buildUser();

        List<User> user = jdbcTemplate.query(selectSql, rowMapper, username);

        try {
            return user.get(0);
        }catch (Exception e){
            return null;
        }
    }

    private User getUserById(String id) {
        String selectSql = "SELECT * from users WHERE users.Id = ?";

        RowMapper<User> rowMapper = buildUser();

        List<User> user = jdbcTemplate.query(selectSql, rowMapper, id);

        try {
            return user.get(0);
        }catch (Exception e){
            return null;
        }
    }

    public RowMapper<User> buildUser(){
         RowMapper<User> rowMapper = (resultSet, rowNo) -> User.builder()
                .id(resultSet.getInt("Id"))
                .username(resultSet.getString("Username"))
                .full_name(resultSet.getString("Full_Name"))
                .pass(resultSet.getString("Password"))
                .role(resultSet.getInt("Role"))
                .build();

         return rowMapper;
    }


    public void rankUp(RankUpDTO rankup){
        User user = getUserById(rankup.getUser_id());
        if (user == null || user.getRole() != 2){
            throw new UserRoleException();
        }
        String sql = "UPDATE users SET users.Role = 2 WHERE users.Id = ?";
        jdbcTemplate.update(sql, rankup.getTarget_id());
    }
}
