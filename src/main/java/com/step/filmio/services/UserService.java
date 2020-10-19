package com.step.filmio.services;

import com.step.filmio.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public String loginUser(String username, String password) {
        var user = userRepository.findByUsernameAndPassword(username, password);

        if (user == null) {
            return null;
        }

        return TokenGenerationService.EncryptId(user.getId());
    }


}
