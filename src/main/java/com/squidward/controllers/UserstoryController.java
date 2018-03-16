package com.squidward.controllers;

import com.squidward.beans.UserStory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserstoryController {

    @RequestMapping("/userStory/new")
    public String addUserStory(@RequestParam("uss_id") int ussID,
                               @RequestParam("story") String story,
                               @RequestParam("difficulty") int difficulty,
                               @RequestParam("done_tag") String doneTag ,
                               @RequestParam("start_tag") String stringTag,
                               @RequestParam("start_Date")Date startDate,
                               @RequestParam("done_date") Date doneDate,
                               @RequestParam("sprint_ID") int sprintID) {
        //TODO: need service for adding userStory

        return "Userstory successfully added";
    }


    @RequestMapping("/userStory/{id}")
    public ResponseEntity<List<UserStory>> getUserStories(@PathVariable("id") int sprintID) {
        List<UserStory> stories = new ArrayList<>();

        //TODO: add all the userstories from the sprint

        return new ResponseEntity<>(stories, HttpStatus.OK);
    }
}
