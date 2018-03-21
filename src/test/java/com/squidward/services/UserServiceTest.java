package com.squidward.services;

import com.squidward.beans.User;
import com.squidward.repos.UserRepo;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.validation.constraints.AssertTrue;

import static org.mockito.Mockito.when;
import static org.junit.Assert.*;

public class UserServiceTest {

    @Mock
    private UserRepo userRepo;


    @InjectMocks
    private UserService userService;


    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void setUserRepo() {
        User user = new User();
        userService.setUserRepo(userRepo);
        assertNotNull(userService);
    }

    @Test
    public void doesUserExist() {
        User user = new User();
        user.setUsername("Jon");
        when(userRepo.save(user)).thenReturn(user);
        boolean result =  userService.doesUserExist(user.getUsername());
        System.out.println(result);
        assertTrue(result);
    }

    @Test
    public void saveUser() {
        User user = new User();
        user.setId(1);
        when(userRepo.save(user)).thenReturn(user);
        User result = userService.saveUser(user);
        assertEquals(1, result.getId());
    }

    @Test
    public void getUser() {
        User user = new User();
        user.setUsername("JS19");
        when(userRepo.getUserByUsername(user.getUsername())).thenReturn(user);
        User result = userService.getUser(user.getUsername());
        assertEquals("JS19", result);

    }
}