package com.squidward.repos;

import com.squidward.beans.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepo extends CrudRepository<Project, Integer> {

    Iterable<Project> findAllByOwnerUsername(String username);
    Iterable<Project> findAllByUsersUsername(String username);
}