
package com.squidward.services;

import com.squidward.beans.Project;
import com.squidward.beans.Sprint;
import com.squidward.daos.SprintRepo;
import org.springframework.stereotype.Service;


import java.util.List;
/*
----Sprint Service---
Get Sprints
Add Sprints
*/

@Service
public class SprintService {
    private SprintRepo sprintrepo;

    //Get Sprint by Project ID
    public List<Sprint> getSprintsById(Project project)
    {
        return sprintrepo.getSprintsByProject(project);
    }

    // Add Sprint
    public Sprint saveSprint(Sprint sprint)
    {
        return sprintrepo.save(sprint);
    }


}