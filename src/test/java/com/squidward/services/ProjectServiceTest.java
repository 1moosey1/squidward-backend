package com.squidward.services;

import com.squidward.beans.Project;
import com.squidward.beans.User;
import com.squidward.repos.ProjectRepo;
import com.squidward.repos.UserRepo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.squidward.utils.GithubConfig;
import org.kohsuke.github.GitHub;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class ProjectServiceTest {
    @Mock
    private ProjectRepo projectRepo;

    @Mock
    private UserRepo userRepo;

    @InjectMocks
    private ProjectService projectService;

    @InjectMocks
    private UserService userService;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void setUserRepo() {
        User user = new User();
        userService.setUserRepo(userRepo);
        assertNotNull(userService);

    }

    @Test
    public void setProjectRepo() {
        Project project = new Project();
        projectService.setProjectRepo(projectRepo);
        assertNotNull(projectService);
    }

    @Test
    public void getOwnedProjects() throws IOException {
        GitHub gitHub = null;
        String username = gitHub.getMyself().getLogin();
        assertNotNull(username);

    }

    @Test
    public void getDeveloperProjects() {

    }

/*
    @Test
    public void saveProject() {

        Project project = new Project();
        project.setId(1);
        when(projectRepo.save(project)).thenReturn(project);
        Project result = projectService.saveProject(project);
        assertEquals(1, result.getId());
    } */

    @Test
    public void deleteProject() {
        Project project = new Project();
        project.setId(1);
        projectService.deleteProject(project.getId());

        verify(projectRepo, times(1)).deleteById(1);
    }
}