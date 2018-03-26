package com.squidward.repos;

import com.squidward.beans.UserStory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface UserStoryRepo extends CrudRepository<UserStory, Integer> {

    Iterable<UserStory> findAllBySprintId(int sprintId);
    Optional<UserStory> findByStartTagAndSprintProjectId(String startTag, int projectId);
    Optional<UserStory> findByDoneTagAndSprintProjectId(String doneTag, int projectId);
    int countBySprintId(int sprintId);

    @Query("select coalesce(sum(difficulty), 0) from UserStory where sprint.id = ?1")
    int getOverallPointSum(int sprintId);

    @Query(value = "select coalesce(sum(difficulty), 0) " +
            "from user_story " +
            "where sprint_id = ?1 and done_date = CAST(?2 AS DATE)",
            nativeQuery = true)
    int getDoneDatePointSum(int sprintId, Date doneDate);
}
