package com.squidward.daos;

import com.squidward.beans.User;
import com.squidward.beans.UserStory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends CrudRepository<User, Integer> {



}
