package com.squidward.beans;

import com.beans.Sprint;
import org.junit.Test;
import org.meanbean.test.BeanTester;

public class SprintServiceTest {
    @Test
    public void testSprint() {
        BeanTester beanTester = new BeanTester();
        beanTester.testBean(Sprint.class);
    }
}
