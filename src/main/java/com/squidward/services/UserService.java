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

    public boolean doesUserExistByUsername(String username) {
        return userRepo.existsByUsername(username);
    }

    public boolean doesUserExistByEmail(String email) {
        return userRepo.existsByEmail(email);
    }

    public Optional<User> getUserByUsername(String username) {
        return userRepo.getUserByUsername(username);
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepo.getUserByEmail(email);
    }

    public Optional<String> getUserOAuthToken(String email) {
        Optional<User> userOptional = userRepo.getUserByEmail(email);
        if (userOptional.isPresent()) {
            return Optional.ofNullable(userOptional.get().getOAuthToken());
        }

        return Optional.empty();
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

    public boolean registerUser(User user) {
        if (hasValidUserFields(user) && !userRepo.existsByEmail(user.getEmail())) {

            user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
            userRepo.save(user);
            return true;
        }

        return false;
    }

    private boolean hasValidUserFields(User user) {
        Validator validator = validatorFactory.getValidator();
        return validator.validate(user).size() == 0;
    }
}
