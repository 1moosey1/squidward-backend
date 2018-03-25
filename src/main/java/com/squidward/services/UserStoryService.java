package com.squidward.services;

import com.squidward.beans.UserStory;
import com.squidward.repos.SprintRepo;
import com.squidward.repos.UserStoryRepo;
import com.squidward.utils.ValidatorFactory;
import lombok.extern.slf4j.Slf4j;
import org.kohsuke.github.GitHub;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.io.IOException;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class UserStoryService {

    private UserStoryRepo userStoryRepo;
    private SprintRepo sprintRepo;
    private ValidatorFactory validatorFactory;

    @Autowired
    public void setUserStoryRepo(UserStoryRepo userStoryRepo) {
        this.userStoryRepo = userStoryRepo;
    }

    @Autowired
    public void setSprintRepo(SprintRepo sprintRepo) {
        this.sprintRepo = sprintRepo;
    }

    @Autowired
    public void setValidatorFactory(ValidatorFactory validatorFactory) {
        this.validatorFactory = validatorFactory;
    }

    public Optional<Iterable<UserStory>> getUserStories(int sprintId, GitHub gitHub)
            throws IOException {
        String username = gitHub.getMyself().getLogin();

        if (!sprintRepo.existsByIdAndProjectOwnerUsername(sprintId, username)) {
            return Optional.empty();
        }

        return Optional.of(userStoryRepo.findAllBySprintId(sprintId));
    }

    public boolean saveUserStory(UserStory userStory, GitHub gitHub)
            throws IOException {
        String username = gitHub.getMyself().getLogin();

        if (!hasValidFields(userStory) ||
                sprintRepo.existsByIdAndProjectOwnerUsername(userStory.getSprint().getId(), username)) {
            return false;
        }

        userStoryRepo.save(userStory);
        String startTag = "story-" + userStory.getSprint().getId()
                + "-" + userStory.getId() + "-s";
        String doneTag = "story-" + userStory.getSprint().getId()
                + "-" + userStory.getId() + "-d";

        userStory.setStartTag(startTag);
        userStory.setDoneTag(doneTag);
        // userStoryRepo.save(userStory);

        return true;
    }

    private boolean hasValidFields(UserStory userStory) {
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<UserStory>> violations = validator.validate(userStory);
        for (ConstraintViolation<UserStory> violation : violations) {
            log.debug(violation.getMessageTemplate() + " " + violation.getMessage());
        }

        return violations.size() == 0;
    }
}
