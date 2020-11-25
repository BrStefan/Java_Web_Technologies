package com.BrStefan.BrStefan.repository;

import com.BrStefan.BrStefan.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {

    private List<User> users;

    public User register(User user){
        users.add(user);
        return user;
    }

    public List<User> getAll(){
        return users;
    }

    public User delete(User user){
        users.remove(user);
        return user;
    }

    public User rankUp(User user){
        //make user.role = 1
        return user;
    }
}
