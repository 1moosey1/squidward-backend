package com.squidward.controllers;

import com.squidward.beans.StatusType;
import com.squidward.beans.UserStory;
import com.squidward.beans.UserStoryStatus;
import com.squidward.services.UserStoryService;
import jdk.net.SocketFlow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserStoryController {

    private UserStoryService userStoryService;
    UserStoryStatus userStoryStatus = new UserStoryStatus();

    @Autowired
    public void setUserStoryService(UserStoryService userStoryService) {
        this.userStoryService = userStoryService;
    }

    @GetMapping("/userstory/{sprintid}")
    public Iterable<UserStory> getUserStories(@PathVariable("sprintid") int sprintId) {
        return userStoryService.getUserStories(sprintId);
    }

    @PostMapping("/userstory/new")
    public UserStory saveUserStory(@RequestBody UserStory userstory) {
        userStoryStatus.setStatusType(StatusType.TODO.toString());
        userstory.setStatus(userStoryStatus);
        return userStoryService.saveUserStories(userstory);
    }

    @PostMapping("/userstory/todo")
    public UserStory todo(@RequestBody UserStory userstory) {
        userStoryStatus.setStatusType(StatusType.TODO.toString());
        userstory.setStatus( userStoryStatus );
        return userStoryService.saveUserStories(userstory);
    }

    @PostMapping("/userstory/progress")
    public UserStory progress(@RequestBody UserStory userstory) {
        userStoryStatus.setStatusType(StatusType.IN_PROGRESS.toString());
        userstory.setStatus( userStoryStatus );
        return userStoryService.saveUserStories(userstory);
    }

    @PostMapping("/userstory/done")
    public UserStory done(@RequestBody UserStory userstory) {
        userStoryStatus.setStatusType(StatusType.DONE.toString());
        userstory.setStatus( userStoryStatus );
        return userStoryService.saveUserStories(userstory);
    }

}
