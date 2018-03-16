package com.squidward.beans;

import net.sf.beanrunner.BeanRunner;
import org.junit.Test;

public class ProjectUserTest {
    @Test
    public void testProjectUser() throws Exception {
        BeanRunner beanRunner = new BeanRunner();
        beanRunner.testBean(new ProjectUser());
    }
}
