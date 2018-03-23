package com.squidward.services;

import com.squidward.beans.Project;
import com.squidward.beans.User;
import com.squidward.configs.ApplicationConfig;
import com.squidward.repos.ProjectRepo;
import com.squidward.repos.UserRepo;
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

        Optional<User> userOptional = userRepo.getUserByUsername(username);
        Optional<Project> projectOptional = projectRepo.findByName(project.getName());

        if (!userOptional.isPresent() ||
            !hasValidFields(project) ||
            (projectOptional.isPresent() &&
             projectOptional.get().getOwner().getUsername().equals(username))) {
            return false;
        }

        User user = userOptional.get();
        GHRepository ghRepository = gitHub.getRepository(username + "/" + project.getName());

        Collection<GHEvent> subscriptions = new ArrayList<>();
        subscriptions.add(GHEvent.PUSH);

        URL url = new URL(appConfig.getWebhook());
        ghRepository.createWebHook(url, subscriptions);

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

    private boolean hasValidFields(Project project) {
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<Project>> violations = validator.validate(project);
        for (ConstraintViolation<Project> violation : violations) {
            log.debug(violation.getMessageTemplate() + " " + violation.getMessage());
        }

        return violations.size() == 0;
    }
}