package com.squidward.controllers;


import com.squidward.beans.Project;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProjectController {

    @RequestMapping(value="/projects/{id}")
    public ResponseEntity<List<Project>> getProjects(@PathVariable("id") int userid) {
        List<Project> projects = new ArrayList<>();

        //TODO: Need to grab project information!!

        return new ResponseEntity <>(projects, HttpStatus.OK);
    }

    @RequestMapping(value = "/projects/new", method = RequestMethod.POST)
    public String addProject(@RequestBody Project project) {
        //TODO: Need Service to add the project

        return "Project Successfully added";
    }

    @RequestMapping(value = "/projects/delete", method = RequestMethod.DELETE)
    public String deleteProject(@RequestParam("projectId") int projectID) {
        //TODO: Need Service to delete the project

        return "projecet successfully deleted";
    }

}
