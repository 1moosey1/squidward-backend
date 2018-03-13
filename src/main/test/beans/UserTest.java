package tests;

import org.junit.Test;
import org.meanbean.test.BeanTester;
import beans.User;

public class UserTest {
    @Test
    public void testUser() {
        BeanTester beanTester = new BeanTester();
        beanTester.testBean(User.class);
    }
}
