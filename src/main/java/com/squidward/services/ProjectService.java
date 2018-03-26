package com.squidward.services;

import com.squidward.beans.*;
import com.squidward.configs.ApplicationConfig;
import com.squidward.repos.*;
import com.squidward.utils.GithubPayload;
import com.squidward.utils.ValidatorFactory;
import lombok.extern.slf4j.Slf4j;
import org.kohsuke.github.GHEvent;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Service
public class ProjectService {

    private ApplicationConfig appConfig;
    private UserRepo userRepo;
    private ProjectRepo projectRepo;
    private UserStoryRepo userStoryRepo;
    private UserStoryStatusRepo userStoryStatusRepo;
    private ValidatorFactory validatorFactory;

    @Autowired
    public void setAppConfig(ApplicationConfig appConfig) {
        this.appConfig = appConfig;
    }

    @Autowired
    public void setUserRepo(UserRepo userRepo) {
        this.userRepo = userRepo;
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
    public void setUserStoryStatusRepo(UserStoryStatusRepo userStoryStatusRepo) {
        this.userStoryStatusRepo = userStoryStatusRepo;
    }

    @Autowired
    public void setValidatorFactory(ValidatorFactory validatorFactory) {
        this.validatorFactory = validatorFactory;
    }

    public Iterable<Project> getOwnedProjects(GitHub gitHub) throws IOException {
        String username = gitHub.getMyself().getLogin();
        return projectRepo.findAllByOwnerUsername(username);
    }

    public Iterable<Project> getDeveloperProjects(GitHub gitHub) throws IOException {
        String username = gitHub.getMyself().getLogin();
        return projectRepo.findAllByUsersUsername(username);
    }

    public boolean saveProject(Project project, GitHub gitHub) throws IOException {
        String username = gitHub.getMyself().getLogin();

        // Check for invalid user or project and existing repo hook
        Optional<User> userOptional = userRepo.getUserByUsername(username);
        if (!userOptional.isPresent() || !hasValidFields(project) ||
                projectRepo.existsByNameAndOwnerUsername(project.getName(), username)) {

            log.debug(Boolean.toString(!userOptional.isPresent()));
            log.debug(Boolean.toString(!hasValidFields(project)));
            log.debug(Boolean.toString(
                    projectRepo.existsByNameAndOwnerUsername(project.getName(), username)));
            return false;
        }

        User user = userOptional.get();
        GHRepository ghRepository = gitHub.getRepository(username + "/" + project.getName());

        Collection<GHEvent> subscriptions = new ArrayList<>();
        subscriptions.add(GHEvent.PUSH);

        Map<String, String> subscriptionConfig = new HashMap<>();
        URL url = new URL(appConfig.getWebhook());

        subscriptionConfig.put("url", url.toExternalForm());
        subscriptionConfig.put("content_type", "application/json");

        ghRepository.createHook("web", subscriptionConfig, subscriptions, true);

        log.debug("Webhook created for " + ghRepository.getName());

        // store the owner into the project
        project.setOwner(user);

        // set date
        Date date = new Date(System.currentTimeMillis());
        project.setStartDate(date);

        // save the project
        projectRepo.save(project);
        return true;
    }

    public void deleteProject(int projectId) {
        projectRepo.deleteById(projectId);
    }

    public void processWebhook(GithubPayload githubPayload) {
        Pattern pattern = Pattern.compile("tag:<(story-[0-9]*-[0-9]*-[sd]{1})>");

        Optional<Project> projectOptional =
                projectRepo.findByName(githubPayload.getRepository().getName());

        if (projectOptional.isPresent()) {

            Project project = projectOptional.get();

            String message, tag;
            Matcher matcher;

            for (GithubPayload.Commit commit : githubPayload.getCommits()) {

                message = commit.getMessage();
                matcher = pattern.matcher(message);

                log.debug("Current Message: " + message);

                while (matcher.find()) {

                    tag = matcher.group(1);
                    log.debug("Found tag: " + tag);

                    Optional<UserStory> userStoryOptional;

                    userStoryOptional = userStoryRepo.findByStartTagAndSprintProjectId(tag, project.getId());
                    userStoryOptional.ifPresent(userStory -> processStartTag(userStory, commit));

                    userStoryOptional = userStoryRepo.findByDoneTagAndSprintProjectId(tag, project.getId());
                    userStoryOptional.ifPresent(userStory -> processDoneTag(userStory, commit));
                }
            }
        }
    }

    private void processStartTag(UserStory userStory, GithubPayload.Commit commit) {
        String statusType = userStory.getStatus().getStatusType();
        if (statusType.equals(StatusType.TODO.toString())) {

            UserStoryStatus userStoryStatus =
                    userStoryStatusRepo.findByStatusType(StatusType.IN_PROGRESS.toString());

            userStory.setStatus(userStoryStatus);
            userStory.setStartDate(new Date(System.currentTimeMillis()));
            userStory.setStartUrl(commit.getUrl());

            userStoryRepo.save(userStory);
        }
    }

    private void processDoneTag(UserStory userStory, GithubPayload.Commit commit) {
        processStartTag(userStory, commit);

        String statusType = userStory.getStatus().getStatusType();
        if (statusType.equals(StatusType.IN_PROGRESS.toString())) {

            UserStoryStatus userStoryStatus =
                    userStoryStatusRepo.findByStatusType(StatusType.DONE.toString());

            userStory.setStatus(userStoryStatus);
            userStory.setDoneDate(new Date(System.currentTimeMillis()));
            userStory.setDoneUrl(commit.getUrl());

            userStoryRepo.save(userStory);
        }
    }

    private boolean hasValidFields(Project project) {
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<Project>> violations = validator.validate(project);
        for (ConstraintViolation<Project> violation : violations) {
            log.debug(violation.getMessageTemplate() + " " + violation.getMessage());
        }

        return violations.size() == 0;
    }
}