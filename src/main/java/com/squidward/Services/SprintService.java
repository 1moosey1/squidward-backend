
package com.squidward.Services;

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

    //Get Sprint by ID
    public List<Sprint> getSprint(int project_id)
    {
        return sprintrepo.getAll();
    }

    // Add Sprint
    public void addSprint(Sprint sprint)
    {
        sprintrepo.save(sprint);
    }


}