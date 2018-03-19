package com.squidward.Services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.squidward.beans.Project;
import com.squidward.daos.ProjectRepo;
import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

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
    public void getAllProjects() {

        List<Project> projectList = new ArrayList<Project>();
        projectList.add(new Project());
        projectList.add(new Project());
        projectList.add(new Project());
        when(projectRepo.findAll()).thenReturn(projectList);

        List<Project> result = projectService.getAllProjects();
        assertEquals(3, result.size());

    }

    @Test
    public void deleteProject() {
       Project project  = new Project();
       projectService.deleteProject(project);
       verify(projectRepo, times(1)).deleteProjects(project);

    }

    @Test
    public void addProject() {
        Project project = new Project();
        project.setId(1);
        project.setName("Project 1");


        when(projectRepo.save(project)).thenReturn(project);
        Project result = projectService.saveProject(project);

        assertEquals(1, result.getId());
        assertEquals("Project 1", result.getName());

    }
}