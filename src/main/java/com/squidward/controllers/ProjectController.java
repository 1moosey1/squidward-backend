package com.squidward.controllers;

import com.squidward.beans.Project;
import com.squidward.beans.User;
import com.squidward.services.ProjectService;
import com.squidward.utils.SquidwardHttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.kohsuke.github.GitHub;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;

@Slf4j
@RestController
@RequestMapping("/api")
public class  ProjectController {

    private ProjectService projectService;

    @Autowired
    public void setProjectService(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping(value="/projects/owned")
    public ResponseEntity<Iterable<Project>> getOwnedProjects(HttpServletRequest httpServletRequest) {
        Iterable<Project> projects = new ArrayList<>();
        GitHub gitHub = ((SquidwardHttpServletRequest) httpServletRequest).getGitHub();

        System.out.println();
        System.out.println();
        System.out.println("1" + gitHub);
        System.out.println();
        System.out.println();



        try{
            projects = projectService.getOwnedProjects(gitHub);
            System.out.println();
            System.out.println();
            System.out.println("2" + projects);
            System.out.println();
            System.out.println();
        } catch (IOException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(projects, HttpStatus.UNAUTHORIZED);
        }

        System.out.println();
        System.out.println();
        System.out.println("3");
        System.out.println();
        System.out.println();

        return new ResponseEntity <>(projects, HttpStatus.OK);
    }

    @GetMapping(value="/projects/developer")
    public ResponseEntity<Iterable<Project>> getDeveloperProjects(HttpServletRequest httpServletRequest) {
        Iterable<Project> projects = new ArrayList<>();
        GitHub gitHub = ((SquidwardHttpServletRequest) httpServletRequest).getGitHub();

        try{
            projects = projectService.getDeveloperProjects(gitHub);
        } catch (IOException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(projects, HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity <>(projects, HttpStatus.OK);
    }

    @PostMapping(value = "/projects/new")
    public Project addProject(HttpServletRequest httpServletRequest, @RequestBody Project project) throws IOException {
        GitHub gitHub = ((SquidwardHttpServletRequest) httpServletRequest).getGitHub();
        return projectService.saveProject(project, gitHub);
    }

    @DeleteMapping(value = "/projects/delete")
    public void deleteProject(@RequestParam("projectId") int projectId) {
        projectService.deleteProject(projectId);
    }
}
