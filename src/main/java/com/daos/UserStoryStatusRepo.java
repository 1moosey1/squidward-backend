package com.daos;

import com.beans.UserStoryStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserStoryStatusRepo extends CrudRepository<UserStoryStatus, Integer> {

}
