package com.squidward.daos;

import com.squidward.beans.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepo extends CrudRepository<Project, Integer> {


    //Get all Projects
    List<Project> findAll();

    //Delete a Project
    void deleteProjectsById(int project_id);


    //Add a Project
    Project saveProject(Project project);


}
