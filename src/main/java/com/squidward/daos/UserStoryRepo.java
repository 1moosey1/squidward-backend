package com.squidward.daos;

<<<<<<< HEAD:src/main/java/com/daos/UserStoryRepo.java
import com.beans.Project;
import com.beans.UserStory;
=======
import com.squidward.beans.UserStory;
>>>>>>> master:src/main/java/com/squidward/daos/UserStoryRepo.java
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserStoryRepo extends CrudRepository<UserStory, Integer> {

    //Get all Projects
    List<UserStory> getById(int id);

}
