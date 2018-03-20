package com.squidward.services;

import com.squidward.beans.User;
import com.squidward.repos.UserRepo;
import com.squidward.utils.JWTUtil;
import com.squidward.utils.ValidatorFactory;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import java.util.Optional;

@Service
public class UserService {

    private UserRepo userRepo;
    private JWTUtil jwtUtil;
    private ValidatorFactory validatorFactory;

    @Autowired
    public void setUserRepo(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Autowired
    public void setJwtUtil(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Autowired
    public void setValidatorFactory(ValidatorFactory validatorFactory) {
        this.validatorFactory = validatorFactory;
    }

    public boolean doesUserExist(String username) {
        return userRepo.existsByUsername(username);
    }

    public User getUser(String username) {
        return userRepo.getUserByUsername(username);
    }

    public User saveUser(User user) {
        return userRepo.save(user);
    }

    public Optional<String> loginUser(User user) {
        if (hasValidUserFields(user)) {

            Optional<User> storedUser = userRepo.getUserByEmail(user.getEmail());
            if (storedUser.isPresent() &&
                    BCrypt.checkpw(user.getPassword(), storedUser.get().getPassword())) {

                String jwt = jwtUtil.createJWT(storedUser.get().getId(), user.getEmail());
                return Optional.of(jwt);
            }
        }

        return Optional.empty();
    }

    public Optional<Integer> registerUser(User user) {
        if (hasValidUserFields(user) && !userRepo.existsByEmail(user.getEmail())) {

            user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
            userRepo.save(user);
            return Optional.of(user.getId());
        }

        return Optional.empty();
    }

    private boolean hasValidUserFields(User user) {
        Validator validator = validatorFactory.getValidator();
        return validator.validate(user).size() == 0;
    }
}
