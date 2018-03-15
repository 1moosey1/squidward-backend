package com.squidward.beans;

import squidward.beans.UserStory;
import org.junit.Test;
import org.meanbean.test.BeanTester;

public class UserStoryTest {
    @Test
    public void testUserStory() {
        BeanTester beanTester = new BeanTester();
        beanTester.testBean(UserStory.class);
    }
}
