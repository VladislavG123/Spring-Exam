package com.step.filmio.services;

import com.step.filmio.models.Role;
import com.step.filmio.models.User;

public class AuthService {

    public static boolean isAdmin(String token, UserService userService) {
        var id = TokenGenerationService.DecryptId(token);
        var user = userService.getUserRepository().findById(id);

        return user.orElse(new User()).getRole() == Role.ADMIN;
    }

    public static User getUser(String token, UserService userService) {
        var id = TokenGenerationService.DecryptId(token);
        return userService.getUserRepository().findById(id).orElse(null);
    }

}
