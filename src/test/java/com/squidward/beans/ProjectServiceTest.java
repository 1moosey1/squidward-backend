package com.squidward.beans;

import net.sf.beanrunner.BeanRunner;
import org.junit.Test;

<<<<<<< HEAD:src/test/java/com/squidward/beans/ProjectServiceTest.java
public class ProjectServiceTest {
=======
public class ProjectTest {

>>>>>>> master:src/test/java/com/squidward/beans/ProjectTest.java
    @Test
    public void testProject() throws Exception {
        BeanRunner beanRunner = new BeanRunner();
        beanRunner.testBean(new Project());
    }
}
