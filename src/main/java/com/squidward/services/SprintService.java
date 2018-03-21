
package com.squidward.services;

import com.squidward.beans.Sprint;
import com.squidward.repos.SprintRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SprintService {

    private SprintRepo sprintRepo;

    @Autowired
    public void setSprintRepo(SprintRepo sprintRepo) {
        this.sprintRepo = sprintRepo;
    }

    public Iterable<Sprint> getSprints(int projectId) {
        return sprintRepo.findAllByProjectId(projectId);
    }

    public Sprint saveSprint(Sprint sprint) {
        return sprintRepo.save(sprint);
    }
}
