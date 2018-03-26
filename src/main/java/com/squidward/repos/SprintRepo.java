package com.squidward.repos;

import com.squidward.beans.Sprint;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SprintRepo extends CrudRepository<Sprint, Integer> {

    boolean existsByIdAndProjectOwnerUsername(int sprintId, String username);
    Optional<Sprint> findByIdAndProjectOwnerUsername(int sprintId, String username);
    Iterable<Sprint> findAllByProjectId(int projectId);
    int countByProjectId(int projectId);
}
