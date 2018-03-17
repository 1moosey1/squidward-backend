package com.daos;

import com.beans.Project;
import org.junit.Test;
import static org.junit.Assert.*;
import junitparams.JUnitParamsRunner;

import org.junit.runners.Parameterized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;


import static org.junit.Assert.*;

public class ProjectRepoTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
   private static ProjectRepo projectrepo;

    @Test
    public void findAll() {


        
        Project project = (Project) projectrepo.findAll();
        assertNotNull(projectrepo.findAll());

    }

    @Test
    public void deleteProjectsById() {

        Project p1 = new Project();
        Project p2 = new Project();
        p1.setId(1);
        p2.setId(2);



      //  assertNotNull(projectrepo.findAll());
    }

    @Test
    public void addProject() {
    }




    @Test
    @RunWith(JUnitParamsRunner.class)
    public void findById(int id) {
        int search = 1;
        Project project = projectrepo.findById(search);
        assertEquals(projectrepo.findById(1).toString(), project.toString());
      //  assertEquals(dao.getAddressById(1).toString(),address.toString());
     




    }


}