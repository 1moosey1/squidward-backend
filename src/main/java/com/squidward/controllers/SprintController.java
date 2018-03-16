package com.squidward.controllers;

import com.squidward.beans.Project;
import com.squidward.beans.Sprint;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class SprintController {

    @RequestMapping(value="/sprint/{id}")
    public ResponseEntity<List<Sprint>>  getSprints(@PathVariable("id") int sprintID) {
        List<Sprint> sprints = new ArrayList<>();

        return new ResponseEntity<>(sprints, HttpStatus.OK);
    }

    @RequestMapping(value = "/sprint/new", method = RequestMethod.POST)
    public String addSprint(@RequestParam("sprint_number") int sprintNumber,
                            @RequestParam("projectID") int projectID,
                            @RequestParam("release") boolean release) {

        return "Sprint successfully added";
    }


}
