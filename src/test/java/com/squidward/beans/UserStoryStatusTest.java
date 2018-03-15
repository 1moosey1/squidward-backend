package com.squidward.beans;

import com.beans.UserStoryStatus;
import org.junit.Test;
import org.meanbean.test.BeanTester;

public class UserStoryStatusTest {
    @Test
    public void testUserStoryStatus() {
        BeanTester beanTester = new BeanTester();
        beanTester.testBean(UserStoryStatus.class);
    }
}
