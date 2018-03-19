package com.squidward.services;

import com.squidward.beans.Project;
import com.squidward.repos.ProjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    private ProjectRepo projectRepo;

    @Autowired
    public void setProjectRepo(ProjectRepo projectRepo) {
        this.projectRepo = projectRepo;
    }

    public Iterable<Project> getProjects() {
        return projectRepo.findAll();
    }

    public Project saveProject(Project project) {
        return projectRepo.save(project);
    }

    public void deleteProject(int projectId) {
        projectRepo.deleteById(projectId);
    }
}