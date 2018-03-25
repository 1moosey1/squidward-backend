package com.squidward.repos;

import com.squidward.beans.UserStory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserStoryRepo extends CrudRepository<UserStory, Integer> {

    Iterable<UserStory> findAllBySprintId(int sprintId);
    int countBySprintId(int sprintId);
}
