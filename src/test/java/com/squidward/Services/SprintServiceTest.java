package com.squidward.Services;


import com.squidward.beans.Project;
import com.squidward.beans.Sprint;
import com.squidward.daos.SprintRepo;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SprintServiceTest {

    @Mock
    private SprintRepo sprintRepo;


    @InjectMocks
    private SprintService sprintService;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getSprintsById() {
        Project project = new Project();
        project.setId(1);
        project.setName("Project 1");

        Sprint sprint = new Sprint();
        sprint.setId(1);
        sprint.setProject(project);

        when(sprintRepo.getSprintsByProject(project)).thenReturn((List<Sprint>) sprint);
      //  Sprint result = sprintService.getSprintsById(project);
        assertEquals(1,sprint.getId());




    }

    @Test
    public void addSprint() {

        Sprint sprint = new Sprint();
        sprint.setId(1);
        sprint.setNumber(1212);
       when(sprintRepo.save(sprint)).thenReturn(sprint);
       Sprint result = sprintService.saveSprint(sprint);
       assertEquals(1, result.getId());
       assertEquals(1212, result.getNumber());

    }
}