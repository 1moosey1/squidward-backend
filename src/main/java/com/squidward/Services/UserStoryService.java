package com.squidward.Services;


import com.squidward.beans.UserStory;
import com.squidward.daos.UserStoryRepo;
import org.springframework.stereotype.Service;


import java.util.List;

/*
----User Story Service---
Get User Story

*/

@Service
public class UserStoryService {

    private UserStoryRepo userstoryrepo;

    //Get User Story by Sprint ID
    public List<UserStory> getUserStory(int sprint_id)
    {
        return userstoryrepo.getById(sprint_id);

    }
}
