package com.squidward.beans;

import net.sf.beanrunner.BeanRunner;
import org.junit.Test;

public class UserStoryServiceStatusTest {
    @Test
    public void testUserStoryStatus() throws Exception {
        BeanRunner beanRunner = new BeanRunner();
        beanRunner.testBean(new UserStoryStatus());
    }
}
