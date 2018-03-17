package com.daos;

import com.beans.Project;
import org.junit.Test;
import static org.junit.Assert.*;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;


import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;


import static org.junit.Assert.*;

@RunWith(JUnitParamsRunner.class)
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
    public void findById() {
        int search = 1;
        System.out.println(p);
        projectrepo.findById(search);
       // Project project = projectrepo.findById(search);
      //  assertEquals(projectrepo.findById(1).toString(), project.toString());
      //  assertEquals(dao.getAddressById(1).toString(),address.toString());


    }


}