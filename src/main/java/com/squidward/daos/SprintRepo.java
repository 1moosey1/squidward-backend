package com.squidward.daos;

import com.squidward.beans.Sprint;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SprintRepo extends CrudRepository<Sprint, Integer> {

    Iterable<Sprint> findAllByProjectId(int projectId);
}
