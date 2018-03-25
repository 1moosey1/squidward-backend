package com.squidward.services;

import com.squidward.beans.Project;
import com.squidward.beans.User;
import com.squidward.configs.ApplicationConfig;
import com.squidward.repos.ProjectRepo;
import com.squidward.repos.UserRepo;
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

@Slf4j
@Service
public class ProjectService {

    private ApplicationConfig appConfig;
    private ProjectRepo projectRepo;
    private UserRepo userRepo;
    private ValidatorFactory validatorFactory;

    @Autowired
    public void setAppConfig(ApplicationConfig appConfig) {
        this.appConfig = appConfig;
    }

    @Autowired
    public void setProjectRepo(ProjectRepo projectRepo) {
        this.projectRepo = projectRepo;
    }

    @Autowired
    public void setUserRepo(UserRepo userRepo) {
        this.userRepo = userRepo;
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

    // TODO: Finish webhook processing
    public void processWebhook(GithubPayload githubPayload) {

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