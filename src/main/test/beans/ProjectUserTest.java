package tests;

import org.junit.Test;
import org.meanbean.test.BeanTester;
import beans.ProjectUser;

public class ProjectUserTest {
    @Test
    public void testProjectUser() {
        BeanTester beanTester = new BeanTester();
        beanTester.testBean(ProjectUser.class);
    }
}
