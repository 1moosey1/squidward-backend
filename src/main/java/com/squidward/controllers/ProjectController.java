package com.squidward.controllers;

import com.squidward.beans.Project;
import com.squidward.services.ProjectService;
import com.squidward.utils.GithubPayload;
import com.squidward.utils.SquidwardHttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.kohsuke.github.GitHub;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

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
        GitHub gitHub = ((SquidwardHttpServletRequest) httpServletRequest).getGitHub();

        try {

            Iterable<Project> projects = projectService.getOwnedProjects(gitHub);
            return new ResponseEntity <>(projects, HttpStatus.OK);

        } catch (IOException e) {

            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping(value="/projects/developer")
    public ResponseEntity<Iterable<Project>> getDeveloperProjects(HttpServletRequest httpServletRequest) {

        GitHub gitHub = ((SquidwardHttpServletRequest) httpServletRequest).getGitHub();

        try {

            Iterable<Project> projects = projectService.getDeveloperProjects(gitHub);
            return new ResponseEntity <>(projects, HttpStatus.OK);

        } catch (IOException e) {

            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping(value = "/projects/new")
    public ResponseEntity<String> addProject(
            HttpServletRequest httpServletRequest,
            @RequestBody Project project) {

        GitHub gitHub = ((SquidwardHttpServletRequest) httpServletRequest).getGitHub();

        try {

            if (!projectService.saveProject(project, gitHub)) {

                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

        } catch(IOException e) {

            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value="/github_webhook")
    public void githubWebhook(@RequestBody GithubPayload pushPayload) {
        projectService.processWebhook(pushPayload);
    }

    @DeleteMapping(value = "/projects/delete")
    public void deleteProject(@RequestParam("projectId") int projectId) {
        projectService.deleteProject(projectId);
    }
}
