package com.squidward.repos;

import com.squidward.beans.UserStory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserStoryRepo extends CrudRepository<UserStory, Integer> {

    Iterable<UserStory> findAllBySprintId(int sprintId);
    Optional<UserStory> findByStartTagAndSprintProjectId(String startTag, int projectId);
    Optional<UserStory> findByDoneTagAndSprintProjectId(String doneTag, int projectId);
    int countBySprintId(int sprintId);
}
