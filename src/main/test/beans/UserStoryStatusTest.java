package tests;

import org.junit.Test;
import org.meanbean.test.BeanTester;
import beans.UserStoryStatus;

public class UserStoryStatusTest {
    @Test
    public void testUserStoryStatus() {
        BeanTester beanTester = new BeanTester();
        beanTester.testBean(UserStoryStatus.class);
    }
}
