package tests;

import org.junit.Test;
import org.meanbean.test.BeanTester;
import beans.Sprint;

public class SprintTest {
    @Test
    public void testSprint() {
        BeanTester beanTester = new BeanTester();
        beanTester.testBean(Sprint.class);
    }
}
