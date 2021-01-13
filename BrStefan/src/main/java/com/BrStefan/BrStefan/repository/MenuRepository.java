package com.BrStefan.BrStefan.repository;

import com.BrStefan.BrStefan.domain.Menu;
import com.BrStefan.BrStefan.domain.User;
import com.BrStefan.BrStefan.domain.dto.MenuDTO;
import com.BrStefan.BrStefan.exceptions.MenuException;
import com.BrStefan.BrStefan.exceptions.StaffException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MenuRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void add(MenuDTO menuDTO){
        String sql = "SELECT * FROM users WHERE users.Id = ?";
        RowMapper<User> rowMapper = (resultSet, rowNo) -> User.builder()
                .role(resultSet.getInt("Role"))
                .build();
        List<User> users = jdbcTemplate.query(sql, rowMapper, menuDTO.getOwner_id());
        if (users.size() == 0 || users.get(0).getRole() != 2){
            throw new MenuException("You are not a member of staff");
        }
        else{
            sql = "INSERT INTO menu (Name, Description) VALUES (?,?)";
            jdbcTemplate.update(sql, menuDTO.getName(), menuDTO.getDescription());
        }
    }

    public List<Menu> all(){
        String sql = "SELECT * FROM menu";
        RowMapper<Menu> rowMapper = (resultSet, rowNo) -> Menu.builder()
                .id(resultSet.getInt("Id"))
                .name(resultSet.getString("Name"))
                .description(resultSet.getString("Description"))
                .build();
        return jdbcTemplate.query(sql, rowMapper);
    }
}
