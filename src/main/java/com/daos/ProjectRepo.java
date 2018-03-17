package com.daos;

import com.beans.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepo extends CrudRepository<Project, Integer> {


    //Get all Projects
    List<Project>findAll();

    //Delete a Project
    void deleteProjectsById(int id);


    //Add a Project
    void addProject(Project project);











}
