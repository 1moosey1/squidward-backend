package com.squidward.beans;

import net.sf.beanrunner.BeanRunner;
import org.junit.Test;


public class ProjectServiceTest {

public class ProjectTest {

    @Test
    public void testProject() throws Exception {
        BeanRunner beanRunner = new BeanRunner();
        beanRunner.testBean(new Project());
    }
}
