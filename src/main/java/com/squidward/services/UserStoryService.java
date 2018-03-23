package com.squidward.services;

import com.squidward.beans.UserStory;
import com.squidward.beans.UserStoryStatus;
import com.squidward.repos.UserStoryRepo;
import com.squidward.repos.UserStoryStatusRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserStoryService {
    private UserStoryStatusRepo userStoryStatusRepo;
    private UserStoryRepo userStoryRepo;

    @Autowired
    public void setUserStoryRepo(UserStoryRepo userStoryRepo) {
        this.userStoryRepo = userStoryRepo;
    }

    public Iterable<UserStory> getUserStories(int sprintId) {
        return userStoryRepo.findAllBySprintId(sprintId);
    }

    public UserStory saveUserStories(UserStory stories) {
        return userStoryRepo.save(stories);
    }
}
