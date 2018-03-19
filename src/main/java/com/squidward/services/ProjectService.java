package com.squidward.services;

import com.squidward.beans.Project;
import com.squidward.repos.ProjectRepo;
import org.kohsuke.github.GitHub;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ProjectService {

    private ProjectRepo projectRepo;

    @Autowired
    public void setProjectRepo(ProjectRepo projectRepo) {
        this.projectRepo = projectRepo;
    }

    public Iterable<Project> getOwnedProjects(GitHub gitHub) throws IOException {
        String username = gitHub.getMyself().getLogin();
        return projectRepo.findAllByOwnerUsername(username);
    }

    public Iterable<Project> getDeveloperProjects(GitHub gitHub) throws IOException {
        String username = gitHub.getMyself().getLogin();
        return projectRepo.findAllByUsersUsername(username);
    }

    public Project saveProject(Project project) {
        return projectRepo.save(project);
    }

    public void deleteProject(int projectId) {
        projectRepo.deleteById(projectId);
    }
}