package com.squidward.beans;

import com.beans.Project;
import org.junit.Test;
import org.meanbean.test.BeanTester;

public class ProjectTest {
    @Test
    public void testProject() {
        BeanTester beanTester = new BeanTester();
        beanTester.testBean(Project.class);
    }
}
