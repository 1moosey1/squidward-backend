package com.squidward.repos;

import com.squidward.beans.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectRepo extends CrudRepository<Project, Integer> {

    boolean existsByIdAndOwnerUsername(int id, String username);
    boolean existsByNameAndOwnerUsername(String name, String username);
    Optional<Project> findByIdAndOwnerUsername(int id, String username);
    Optional<Project> findByNameAndOwnerUsername(String name, String username);
    Iterable<Project> findAllByOwnerUsername(String username);
    Iterable<Project> findAllByUsersUsername(String username);
}
