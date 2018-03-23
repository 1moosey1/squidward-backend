package com.squidward.controllers;

import com.squidward.beans.Project;
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
public class ProjectController {

    private ProjectService projectService;

    @Autowired
    public void setProjectService(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping(value="/projects/owned")
    public ResponseEntity<Iterable<Project>> getOwnedProjects(HttpServletRequest httpServletRequest) {
        Iterable<Project> projects = new ArrayList<>();
        GitHub gitHub = ((SquidwardHttpServletRequest) httpServletRequest).getGitHub();

        try{
            projects = projectService.getOwnedProjects(gitHub);
        } catch (IOException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(projects, HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity <>(projects, HttpStatus.OK);
    }

    @GetMapping(value="/projects/developer")
    public ResponseEntity<Iterable<Project>> getDeveloperProjects(HttpServletRequest httpServletRequest) {
        Iterable<Project> projects = new ArrayList<>();
        GitHub gitHub = ((SquidwardHttpServletRequest) httpServletRequest).getGitHub();

        try {
            projects = projectService.getDeveloperProjects(gitHub);
        } catch (IOException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(projects, HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity <>(projects, HttpStatus.OK);
    }

    @PostMapping(value = "/projects/new")
    public ResponseEntity<String> addProject(HttpServletRequest httpServletRequest,
                                             @RequestBody Project project) {
        GitHub gitHub = ((SquidwardHttpServletRequest) httpServletRequest).getGitHub();

        try {

            if (!projectService.saveProject(project, gitHub)) {

                return new ResponseEntity<>("Invalid Project / User", HttpStatus.BAD_REQUEST);
            }

        } catch(IOException e) {

            log.error(e.getMessage());
            return new ResponseEntity<>("Invalid Github Credentials / Repo Name",
                    HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Webhook created", HttpStatus.OK);
    }

    @PostMapping(value="/github_webhook", consumes = "text/plain")
    public void githubWebhook(@RequestBody String payload) {
        log.debug(payload);
    }

    @DeleteMapping(value = "/projects/delete")
    public void deleteProject(@RequestParam("projectId") int projectId) {
        projectService.deleteProject(projectId);
    }
}
