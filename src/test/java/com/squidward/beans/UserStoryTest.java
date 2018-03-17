package com.squidward.beans;

import net.sf.beanrunner.BeanRunner;
import org.junit.Test;

public class UserStoryTest {
    @Test
    public void testUserStory() throws Exception {
        BeanRunner beanRunner = new BeanRunner();
        beanRunner.testBean(new UserStory());
    }
}
