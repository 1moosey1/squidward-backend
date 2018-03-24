package com.squidward.services;

import com.squidward.beans.Project;
import com.squidward.beans.Sprint;
import com.squidward.repos.ProjectRepo;
import com.squidward.repos.SprintRepo;
import org.kohsuke.github.GitHub;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
public class SprintService {

    private SprintRepo sprintRepo;
    private ProjectRepo projectRepo;

    @Autowired
    public void setSprintRepo(SprintRepo sprintRepo) {
        this.sprintRepo = sprintRepo;
    }

    @Autowired
    public void setProjectRepo(ProjectRepo projectRepo) {
        this.projectRepo = projectRepo;
    }

    public Optional<Iterable<Sprint>> getSprints(GitHub gitHub, int projectId)
            throws IOException {

        String username = gitHub.getMyself().getLogin();
        Optional<Project> projectOptional = projectRepo.findByIdAndOwnerUsername(projectId, username);

        if (!projectOptional.isPresent()) {
            return Optional.empty();
        }

        return Optional.of(sprintRepo.findAllByProjectId(projectId));
    }

    public Sprint saveSprint(Sprint sprint) {
        return sprintRepo.save(sprint);
    }

    private void hadValidFields(Sprint sprint) {

    }
}
