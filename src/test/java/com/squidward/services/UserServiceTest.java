package com.squidward.services;

import com.squidward.beans.User;
import com.squidward.daos.UserRepo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
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
        user.setUsername("JS1993");
        userRepo.existsByUsername(user.getUsername());
        boolean result = userService.doesUserExist(user.getUsername());
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
}