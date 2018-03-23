package com.squidward.repos;

import com.squidward.beans.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectRepo extends CrudRepository<Project, Integer> {

    boolean existsByName(String name);
    Optional<Project> findByName(String name);
    Iterable<Project> findAllByOwnerUsername(String username);
    Iterable<Project> findAllByUsersUsername(String username);
}
