package com.squidward.services;

import com.squidward.beans.Project;
import com.squidward.beans.User;
import com.squidward.repos.ProjectRepo;
import com.squidward.repos.UserRepo;
import org.kohsuke.github.GitHub;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ProjectService {

    private ProjectRepo projectRepo;
    private UserRepo userRepo;

    @Autowired
    public void setUserRepo(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

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

    public Project saveProject(Project project, GitHub gitHub) throws IOException {
        //need owner user
        String username = gitHub.getMyself().getLogin();

        User u = userRepo.getUserByUsername(username);
        //store the owner into the project.
        project.setOwner(u);

//        project.setStartDate();

        //save the project.
        return projectRepo.save(project);
        //save this project to the user's project list
    }

    public void deleteProject(int projectId) {
        projectRepo.deleteById(projectId);
    }
}