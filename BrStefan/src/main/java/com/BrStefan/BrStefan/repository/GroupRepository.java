package com.BrStefan.BrStefan.repository;

import com.BrStefan.BrStefan.domain.Group;
import com.BrStefan.BrStefan.domain.Reservation;
import com.BrStefan.BrStefan.domain.User;
import com.BrStefan.BrStefan.domain.dto.AddUserGroupDTO;
import com.BrStefan.BrStefan.domain.dto.CreateGroupDTO;
import com.BrStefan.BrStefan.exceptions.AddMemberGroupException;
import com.BrStefan.BrStefan.exceptions.CreateGroupException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GroupRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void create(CreateGroupDTO createGroupDTO){
        String sql = "SELECT * FROM users WHERE users.Id=?";
        RowMapper<com.BrStefan.BrStefan.domain.User> rowMapper = (resultSet, rowNo) -> User.builder()
                .role(resultSet.getInt("Role"))
                .build();
        List<User> users = jdbcTemplate.query(sql, rowMapper, createGroupDTO.getUser_id());
        if(users.size() == 0)
            throw new CreateGroupException("No users found for this id");
        else if(users.get(0).getRole() != 2)
            throw new CreateGroupException("You are not a member of staff!");
        else{
            sql = "INSERT INTO groups(Name, Owner) VALUES(?,?)";
            jdbcTemplate.update(sql, createGroupDTO.getName(), createGroupDTO.getUser_id());
        }
    }

    public void addToGroup(AddUserGroupDTO addUserGroupDTO){
        String sql = "SELECT * FROM users WHERE users.Id=?";
        RowMapper<com.BrStefan.BrStefan.domain.User> rowMapper = (resultSet, rowNo) -> User.builder()
                .role(resultSet.getInt("Role"))
                .build();
        List<User> users = jdbcTemplate.query(sql, rowMapper, addUserGroupDTO.getUser_id());
        if(users.size() == 0)
            throw new AddMemberGroupException("No users found for this id");
        else if(users.get(0).getRole() != 2)
            throw new AddMemberGroupException("You are not a member of staff!");
        else{
            users = jdbcTemplate.query(sql, rowMapper, addUserGroupDTO.getCustomer_id());
            if(users.size() == 0)
                throw new AddMemberGroupException("No users found for this customer id!");
            else{
                sql = "SELECT * FROM groups WHERE groups.Id = ?";
                RowMapper<Group> rowMapper1 = buildGroup();
                List<Group> groups = jdbcTemplate.query(sql, rowMapper1, addUserGroupDTO.getGroup_id());
                if(groups.size() == 0)
                    throw new AddMemberGroupException("Group not found");
                else{
                    sql = "INSERT INTO user_group (user_id, group_id) VALUES (?,?)";
                    jdbcTemplate.update(sql, addUserGroupDTO.getCustomer_id(), addUserGroupDTO.getGroup_id());
                }
            }
        }
    }

    private RowMapper<Group> buildGroup() {
        RowMapper<Group> rowMapper = (resultSet, rowNo) -> Group.builder()
                .id(resultSet.getInt("Id"))
                .user_id(resultSet.getInt("owner"))
                .name(resultSet.getString("Name"))
                .build();
        return rowMapper;
    }
}
