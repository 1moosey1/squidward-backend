package tests;

import org.junit.Test;
import org.meanbean.test.BeanTester;
import beans.Project;

public class ProjectTest {
    @Test
    public void testProject() {
        BeanTester beanTester = new BeanTester();
        beanTester.testBean(Project.class);
    }
}
