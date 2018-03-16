package com.squidward.controllers;

import com.squidward.beans.UserStory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserstoryController {

    @RequestMapping("/userStory/new")
    public String addUserStory(@RequestBody UserStory userStory) {
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
