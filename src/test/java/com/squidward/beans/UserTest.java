package com.squidward.beans;

import com.beans.User;
import org.junit.Test;
import org.meanbean.test.BeanTester;

public class UserTest {
    @Test
    public void testUser() {
        BeanTester beanTester = new BeanTester();
        beanTester.testBean(User.class);
    }
}
