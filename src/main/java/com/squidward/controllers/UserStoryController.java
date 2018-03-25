package com.squidward.controllers;

import com.squidward.beans.UserStory;
import com.squidward.services.UserStoryService;
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
public class UserStoryController {

    private UserStoryService userStoryService;

    @Autowired
    public void setUserStoryService(UserStoryService userStoryService) {
        this.userStoryService = userStoryService;
    }

    @GetMapping("/userstory/{sprintid}")
    public ResponseEntity<Iterable<UserStory>> getUserStories(
            HttpServletRequest httpServletRequest,
            @PathVariable("sprintid") int sprintId) {

        GitHub gitHub = ((SquidwardHttpServletRequest) httpServletRequest).getGitHub();

        try {

            Optional<Iterable<UserStory>> userStoriesOptional =
                    userStoryService.getUserStories(sprintId, gitHub);
            if (!userStoriesOptional.isPresent()) {

                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

            } else {

                return new ResponseEntity<>(userStoriesOptional.get(), HttpStatus.OK);
            }

        } catch(IOException e) {

            log.debug(e.getMessage());
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/userstory/new")
    public ResponseEntity<String> saveUserStory(
            HttpServletRequest httpServletRequest,
            @RequestBody UserStory userStory) {

        GitHub gitHub = ((SquidwardHttpServletRequest) httpServletRequest).getGitHub();

        try {

            if (!userStoryService.saveUserStory(userStory, gitHub)) {

                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

            } else {

                return new ResponseEntity<>(HttpStatus.OK);
            }

        } catch(IOException e) {

            log.debug(e.getMessage());
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
}
