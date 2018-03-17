package com.squidward.beans;

import net.sf.beanrunner.BeanRunner;
import org.junit.Test;

public class SprintServiceTest {
    @Test
    public void testSprint() throws Exception {
        BeanRunner beanRunner = new BeanRunner();
        beanRunner.testBean(new Sprint());
    }
}
