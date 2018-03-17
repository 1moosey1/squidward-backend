package com.squidward.daos;

<<<<<<< HEAD:src/main/java/com/daos/SprintRepo.java
import com.beans.Project;
import com.beans.Sprint;
=======
import com.squidward.beans.Sprint;
>>>>>>> master:src/main/java/com/squidward/daos/SprintRepo.java
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SprintRepo extends CrudRepository<Sprint, Integer> {

    //Get all Sprints
    List<Sprint> getAll();

    //Add a Project
  //  void addSprint(Sprint sprint);

}
