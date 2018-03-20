package com.squidward.services;

import com.squidward.beans.Project;
import com.squidward.daos.ProjectRepo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class ProjectServiceTest {

    @Mock
    private ProjectRepo projectRepo;

    @InjectMocks
    private ProjectService projectService;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void setProjectRepo() {
        Project project = new Project();
        projectService.setProjectRepo(projectRepo);

    }

    @Test
    public void getProjects() {

        List<Project> projectList = new ArrayList<Project>();
        projectList.add(new Project());
        when(projectRepo.findAll()).thenReturn(projectList);
        List<Project> result = (List<Project>) projectService.getProjects();
        assertEquals(1, result.size());

    }

    @Test
    public void saveProject() {

        Project project = new Project();
        project.setId(1);
        when(projectRepo.save(project)).thenReturn(project);
        Project result = projectService.saveProject(project);
        assertEquals(1, result.getId());


    }

    @Test
    public void deleteProject() {
        Project project = new Project();
        project.setId(1);
        projectService.deleteProject(project.getId());

        verify(projectRepo, times(1)).deleteProjectsById(1);
    }
}