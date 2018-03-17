package com.services;

import com.beans.Project;
import com.daos.ProjectRepo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;



public class ProjectServiceTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ProjectRepo projectrepo;

    @Test
    public void getAllProjects() {

        Project p1 = new Project();
        Project p2 = new Project();
        p1.setName("p1");
        p2.setName("p2");

        entityManager.persist(p1);
        entityManager.persist(p2);

        entityManager.flush();

        Project found = (Project) projectrepo.findAll();

     //   assertThat(p1).isNotEmpty();
    //    assertThat(p1).isEqualTo(alex.getName());


    }

    @Test
    public void deleteProject() {
    }

    @Test
    public void addProject() {
    }
}