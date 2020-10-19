package com.step.filmio.services;

import org.hibernate.id.GUIDGenerator;

public class TokenGenerationService {

    private static String secret = "super-token-";

    public static String EncryptId(Long id) {
        return secret + id;
    }

    public static Long DecryptId(String token) {
        return Long.parseLong(token.replaceAll(secret, ""));
    }
}
