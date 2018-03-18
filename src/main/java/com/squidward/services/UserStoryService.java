package com.squidward.services;

import com.squidward.beans.UserStory;
import com.squidward.daos.UserStoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserStoryService {

    private UserStoryRepo userStoryRepo;

    @Autowired
    public void setUserStoryRepo(UserStoryRepo userStoryRepo) {
        this.userStoryRepo = userStoryRepo;
    }

    public Iterable<UserStory> getUserStories(int sprintId) {
        return userStoryRepo.findAllBySprintId(sprintId);
    }

    public Iterable<UserStory> saveUserStories(Iterable<UserStory> stories) {
        return userStoryRepo.saveAll(stories);
    }
}
