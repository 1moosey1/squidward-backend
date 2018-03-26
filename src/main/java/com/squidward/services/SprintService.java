package com.squidward.services;

import com.squidward.beans.BurnDownData;
import com.squidward.beans.Sprint;
import com.squidward.repos.ProjectRepo;
import com.squidward.repos.SprintRepo;
import com.squidward.repos.UserStoryRepo;
import com.squidward.utils.ValidatorFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.time.DateUtils;
import org.kohsuke.github.GitHub;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Slf4j
@Service
public class SprintService {

    private SprintRepo sprintRepo;
    private ProjectRepo projectRepo;
    private UserStoryRepo userStoryRepo;
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
    public void setUserStoryRepo(UserStoryRepo userStoryRepo) {
        this.userStoryRepo = userStoryRepo;
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

    public Optional<BurnDownData> burnDownChart(int sprintId, GitHub gitHub) throws IOException {
        String username = gitHub.getMyself().getLogin();

        Optional<Sprint> sprintOptional =
                sprintRepo.findByIdAndProjectOwnerUsername(sprintId, username);

        if (!sprintOptional.isPresent()) {
            return Optional.empty();
        }

        BurnDownData burnDownData = new BurnDownData();
        Sprint sprint = sprintOptional.get();

        int sum = userStoryRepo.getOverallPointSum(sprintId), sumOnDay;
        List<Date> dates = new ArrayList<>();
        List<Integer> points = new ArrayList<>();

        burnDownData.setSum(sum);
        burnDownData.setDates(dates);
        burnDownData.setPoints(points);

        Date startDate = sprint.getStartDate();
        Date endDate = sprint.getEndDate();

        while (endDate.compareTo(startDate) >= 0) {

            sumOnDay = userStoryRepo.getDoneDatePointSum(sprintId, (Date) startDate.clone());
            sum -= sumOnDay;

            dates.add((Date) startDate.clone());
            points.add(sum);
            startDate = DateUtils.addDays(startDate, 1);
        }

        return Optional.of(burnDownData);
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
