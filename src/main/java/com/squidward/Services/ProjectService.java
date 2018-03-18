package com.squidward.Services;


import com.squidward.beans.Project;
import com.squidward.daos.ProjectRepo;
import org.springframework.stereotype.Service;

import java.util.List;


/*
----Project Service---
Get Projects -> List
Adding Projects
Deleting Projects

*/


@Service
public class ProjectService {
    private ProjectRepo projectrepo;


    //Get All Projects
    public List<Project> getAllProjects()
    {
        return projectrepo.findAll();
    }

    //Delete Project by ID
    public void deleteProject(int project_id)
    {
        projectrepo.deleteProjectsById(project_id);
    }

    //Add project
    public void addProject(Project project)
    {

        projectrepo.save(project);

    }


}