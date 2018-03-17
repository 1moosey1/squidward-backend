package com.daos;

import com.beans.Project;
import com.beans.UserStory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserStoryRepo extends CrudRepository<UserStory, Integer> {

    //Get all Projects
    List<UserStory> getById(int id);

}
