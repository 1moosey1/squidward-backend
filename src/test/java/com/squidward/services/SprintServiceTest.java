package com.squidward.services;

import com.squidward.beans.Project;
import com.squidward.beans.Sprint;
import com.squidward.repos.SprintRepo;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.when;

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
    public void setSprintRepo() {
        Sprint sprint = new Sprint();
        sprintService.setSprintRepo(sprintRepo);
        assertNotNull(sprintService);
    }

    @Test
    public void getSprints() {
        List<Sprint> sprintList = new ArrayList<>();
        Sprint sprint1 = new Sprint();
        Project project = new Project();
        sprint1.setProject(project);
        sprintList.add(sprint1);
        when(sprintRepo.findAll()).thenReturn(sprintList);
        List <Sprint> result = (List<Sprint>) sprintService.getSprints(sprint1.getId());
        // assertEquals(1, result.size());
    }

    @Test
    public void saveSprint() {
        Sprint sprint = new Sprint();
        sprint.setNumber(1);
        when(sprintRepo.save(sprint)).thenReturn(sprint);
        Sprint result = sprintService.saveSprint(sprint);
        assertEquals(1, result.getNumber());
    }
}