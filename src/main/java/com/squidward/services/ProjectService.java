package com.squidward.services;

import com.squidward.beans.Project;
import com.squidward.beans.User;
import com.squidward.repos.ProjectRepo;
import com.squidward.repos.UserRepo;
import org.kohsuke.github.GitHub;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

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
        Optional<User> userOptional = userRepo.getUserByUsername(username);

        //store the owner into the project
        userOptional.ifPresent(project::setOwner);

        //set date
        Date date = new Date(System.currentTimeMillis());
        project.setStartDate(date);

        //save the project.
        return projectRepo.save(project);
    }

    public void deleteProject(int projectId) {
        projectRepo.deleteById(projectId);
    }
}