package com.daos;

import com.beans.UserStory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserStoryRepo extends CrudRepository<UserStory, Integer> {

}
