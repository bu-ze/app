package org.social.app.service;

import org.social.app.model.User;
import org.social.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void registerUser(User user){
        if(userRepository.findBYUsername(user.getUsername()).isPresent()){
            throw new IllegalArgumentException("username exist");
        }
        userRepository.save(user);
    }

    public boolean usernameExists(String username){
        return userRepository.findBYUsername(username).isPresent();
    }

    public User loginUser(String password, String username){
        Optional<User> optionalUser = userRepository.findBYUsername(username);
        if (optionalUser.isPresent() && optionalUser.get().getPassword().equals(password)){
            return optionalUser.get();
        }
        throw new IllegalArgumentException("invalid username or password");
    }
}
