package com.squidward.beans;

import net.sf.beanrunner.BeanRunner;
import org.junit.Test;

public class UserTest {
    @Test
    public void testUser() throws Exception {
        BeanRunner beanRunner = new BeanRunner();
        beanRunner.testBean(new User());
    }
}
