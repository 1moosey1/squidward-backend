package com.squidward.controllers;

import com.squidward.beans.Sprint;
import com.squidward.services.SprintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SprintController {

    private SprintService sprintService;

    @Autowired
    public void setSprintService(SprintService sprintService) {
        this.sprintService = sprintService;
    }

    @GetMapping(value="/sprint/{projectid}")
    public Iterable<Sprint> getSprints(@PathVariable("projectid") int projectId) {
        return sprintService.getSprints(projectId);
    }

    @PostMapping(value = "/sprint/new")
    public Sprint addSprint(@RequestBody Sprint sprint) {
        return sprintService.saveSprint(sprint);
    }
}
