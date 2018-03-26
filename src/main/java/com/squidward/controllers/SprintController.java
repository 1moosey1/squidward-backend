package com.squidward.controllers;

import com.squidward.beans.BurnDownData;
import com.squidward.beans.Sprint;
import com.squidward.services.SprintService;
import com.squidward.utils.SquidwardHttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.kohsuke.github.GitHub;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api")
public class SprintController {

    private SprintService sprintService;

    @Autowired
    public void setSprintService(SprintService sprintService) {
        this.sprintService = sprintService;
    }

    @GetMapping(value="/sprint/{projectid}")
    public ResponseEntity<Iterable<Sprint>> getSprints(
            HttpServletRequest httpServletRequest,
            @PathVariable("projectid") int projectId) {

        GitHub gitHub = ((SquidwardHttpServletRequest) httpServletRequest).getGitHub();

        try {

            Optional<Iterable<Sprint>> sprintsOptional = sprintService.getSprints(gitHub, projectId);
            if (!sprintsOptional.isPresent()) {

                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

            } else {

                return new ResponseEntity<>(sprintsOptional.get(), HttpStatus.OK);
            }

        } catch(IOException e) {

            log.debug(e.getMessage());
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping(value = "/sprint/new")
    public ResponseEntity<String> addSprint(
            HttpServletRequest httpServletRequest,
            @RequestBody Sprint sprint) {

        GitHub gitHub = ((SquidwardHttpServletRequest) httpServletRequest).getGitHub();

        try {

            if (!sprintService.saveSprint(sprint, gitHub)) {

                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

            } else {

                return new ResponseEntity<>(HttpStatus.OK);
            }

        } catch(IOException e) {

            log.debug(e.getMessage());
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping(value = "/sprint/burndown/{sprintid}")
    public ResponseEntity<BurnDownData> burnDownChart(
            HttpServletRequest httpServletRequest,
            @PathVariable("sprintid") int sprintId) {

        GitHub gitHub = ((SquidwardHttpServletRequest) httpServletRequest).getGitHub();

        try {

            Optional<BurnDownData> burnDownDataOptional = sprintService.burnDownChart(sprintId, gitHub);
            if (!burnDownDataOptional.isPresent()) {

                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

            } else {

                return new ResponseEntity<>(burnDownDataOptional.get(), HttpStatus.OK);
            }

        } catch(IOException e) {

            log.debug(e.getMessage());
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
}
