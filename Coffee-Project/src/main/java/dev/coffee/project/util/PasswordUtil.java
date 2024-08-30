package dev.coffee.project.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordUtil {
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public static String hashPassword(String password) {
        return passwordEncoder.encode(password);
    }

    public static boolean validatePassword(String password, String hashedPassword) {
        return passwordEncoder.matches(password, hashedPassword);
    }

    public static boolean isPasswordValid(String password) {
        return password.length() >= 8;
    }
}
