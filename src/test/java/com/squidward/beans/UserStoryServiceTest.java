package com.squidward.beans;

import com.beans.UserStory;
import org.junit.Test;
import org.meanbean.test.BeanTester;

public class UserStoryServiceTest {
    @Test
    public void testUserStory() {
        BeanTester beanTester = new BeanTester();
        beanTester.testBean(UserStory.class);
    }
}
