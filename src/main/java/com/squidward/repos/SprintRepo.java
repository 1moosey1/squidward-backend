package com.squidward.repos;

import com.squidward.beans.Sprint;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SprintRepo extends CrudRepository<Sprint, Integer> {

    boolean existsByIdAndProjectOwnerUsername(int sprintId, String username);
    Iterable<Sprint> findAllByProjectId(int projectId);
    int countByProjectId(int projectId);
}
