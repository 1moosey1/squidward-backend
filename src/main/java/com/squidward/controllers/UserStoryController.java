package com.squidward.controllers;

import com.squidward.beans.UserStory;
import com.squidward.beans.UserStoryStatus;
import com.squidward.services.UserStoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/usertory/new")
    public UserStory saveUserStory(@RequestBody UserStory userstory) {
        UserStoryStatus userStoryStatus = new UserStoryStatus();
        userStoryStatus.setStatusType("TODO");
        userstory.setStatus(userStoryStatus);

        return userStoryService.saveUserStories(userstory);
    }
}
