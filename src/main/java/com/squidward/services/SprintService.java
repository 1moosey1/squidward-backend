package com.squidward.services;

import com.squidward.beans.Sprint;
import com.squidward.repos.ProjectRepo;
import com.squidward.repos.SprintRepo;
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
public class SprintService {

    private SprintRepo sprintRepo;
    private ProjectRepo projectRepo;
    private ValidatorFactory validatorFactory;

    @Autowired
    public void setSprintRepo(SprintRepo sprintRepo) {
        this.sprintRepo = sprintRepo;
    }

    @Autowired
    public void setProjectRepo(ProjectRepo projectRepo) {
        this.projectRepo = projectRepo;
    }

    @Autowired
    public void setValidatorFactory(ValidatorFactory validatorFactory) {
        this.validatorFactory = validatorFactory;
    }

    public Optional<Iterable<Sprint>> getSprints(GitHub gitHub, int projectId) throws IOException {
        String username = gitHub.getMyself().getLogin();

        if (!projectRepo.existsByIdAndOwnerUsername(projectId, username)) {
            return Optional.empty();
        }

        return Optional.of(sprintRepo.findAllByProjectId(projectId));
    }

    public boolean saveSprint(Sprint sprint, GitHub gitHub) throws IOException {
        String username = gitHub.getMyself().getLogin();

        if (!hasValidFields(sprint) ||
                !projectRepo.existsByIdAndOwnerUsername(sprint.getProject().getId(), username)) {
            return false;
        }

        sprint.setNumber(sprintRepo.countByProjectId(sprint.getProject().getId()) + 1);

        sprintRepo.save(sprint);
        return true;
    }

    private boolean hasValidFields(Sprint sprint) {
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<Sprint>> violations = validator.validate(sprint);
        for (ConstraintViolation<Sprint> violation : violations) {
            log.debug(violation.getMessageTemplate() + " " + violation.getMessage());
        }

        return violations.size() == 0;
    }
}
