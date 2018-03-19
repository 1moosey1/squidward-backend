package com.squidward.services;

import com.squidward.beans.User;
import com.squidward.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepo userRepo;

    @Autowired
    public void setUserRepo(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public boolean doesUserExist(String username) {
        return userRepo.existsByUsername(username);
    }

    public User saveUser(User user) {
        return userRepo.save(user);
    }
}
