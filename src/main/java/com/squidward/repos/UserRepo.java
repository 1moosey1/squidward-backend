package com.squidward.repos;

import com.squidward.beans.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends CrudRepository<User, Integer> {

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    User getUserByUsername(String username);
    Optional<User> getUserByEmail(String email);
}
