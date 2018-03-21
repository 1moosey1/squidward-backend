package com.squidward.services;

import com.squidward.beans.User;
import com.squidward.repos.UserRepo;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

public class AuthServiceTest {
    @Mock
    private UserRepo userRepo;

    @InjectMocks
    private UserService userService;


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void setUserService() {
        User user = new User();
        userService.setUserRepo(userRepo);
        assertNotNull(userService);

    }

    @Test
    public void setGithubConfig() {
    }

    @Test
    public void login() {
    }
}