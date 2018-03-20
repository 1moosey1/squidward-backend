package com.squidward.controllers;

import com.squidward.beans.Project;
import com.squidward.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProjectController {

    private ProjectService projectService;

    @Autowired
    public void setProjectService(ProjectService projectService) {
        this.projectService = projectService;
    }

    @RequestMapping(value="/projects")
    public ResponseEntity<List<Project>> getProjects() {
        List<Project> projects = new ArrayList<>();
        //TODO: Need to grab projects using OAuth token
        return new ResponseEntity <>(projects, HttpStatus.OK);
    }

    @PostMapping(value = "/projects/new")
    public Project addProject(@RequestBody Project project) {
        return projectService.saveProject(project);
    }

    @DeleteMapping(value = "/projects/delete")
    public void deleteProject(@RequestParam("projectId") int projectId) {
        projectService.deleteProject(projectId);
    }
}
