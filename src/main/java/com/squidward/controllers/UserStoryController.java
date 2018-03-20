package com.squidward.controllers;

import com.squidward.beans.UserStory;
import com.squidward.services.UserStoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserStoryController {

    private UserStoryService userStoryService;

    @Autowired
    public void setUserStoryService(UserStoryService userStoryService) {
        this.userStoryService = userStoryService;
    }

    @GetMapping("/userstory/{sprintid}")
    public Iterable<UserStory> getUserStories(@PathVariable("sprintid") int sprintId) {
        return userStoryService.getUserStories(sprintId);
    }
}
