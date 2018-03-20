package com.squidward.services;

import com.squidward.beans.Project;
import com.squidward.beans.Sprint;
import com.squidward.daos.SprintRepo;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

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

    }

    @Test
    public void getSprints() {
        List<Sprint> sprintList = new ArrayList<Sprint>();
        Sprint sprint1 = new Sprint();
        Project project = new Project();
        project.setId(1);
        sprint1.setProject(project);
        sprintList.add(sprint1);
        when(sprintRepo.findAll()).thenReturn(sprintList);
        List <Sprint> result = (List<Sprint>) sprintService.getSprints(project.getId());
        assertEquals(1, result.size());
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