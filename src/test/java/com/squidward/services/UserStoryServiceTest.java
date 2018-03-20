package com.squidward.services;

import com.squidward.beans.UserStory;
import com.squidward.daos.UserStoryRepo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserStoryServiceTest {

    @Mock
    private UserStoryRepo userStoryRepo;


    @InjectMocks
    private UserStoryService userStoryService;
    private UserService userService;


    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void setUserStoryRepo() {
        UserStory userStory = new UserStory();
        userStoryService.setUserStoryRepo(userStoryRepo);
        assertNotNull(userStoryService);
    }

    @Test
    public void getUserStories() {
        List<UserStory> userStoryList = new ArrayList<UserStory>();
        UserStory userStory1 = new UserStory();
        userStory1.setId(1);
        userStoryList.add(userStory1);
        when(userStoryRepo.findAllBySprintId(1)).thenReturn(userStoryList);
        List<UserStory> result = (List<UserStory>) userStoryService.getUserStories(userStory1.getId());
        assertEquals(1, result.size());
    }

    @Test
    public void saveUserStories() {
        List<UserStory> userStoryList = new ArrayList<UserStory>();
        UserStory userStory = new UserStory();
        userStoryList.add(userStory);
        when(userStoryRepo.saveAll(userStoryList)).thenReturn(userStoryList);
       List <UserStory> result = (List<UserStory>) userStoryService.saveUserStories(userStoryList);
        assertEquals(1, result.size());







    }
}