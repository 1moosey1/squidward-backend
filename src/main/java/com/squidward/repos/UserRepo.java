package com.squidward.repos;

import com.squidward.beans.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<User, Integer> {

    boolean existsByUsername(String username);

    User getUserByUsername(String username);
}
