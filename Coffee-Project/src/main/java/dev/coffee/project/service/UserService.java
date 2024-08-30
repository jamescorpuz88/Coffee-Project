package dev.coffee.project.service;

import dev.coffee.project.entity.User;
import dev.coffee.project.repository.UserRepository;
import dev.coffee.project.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity<String> loginUser(User user) {
        User userData = userRepository.findByUsername(user.getUsername());

        if(userData == null) {
            return ResponseEntity.badRequest().body("No user found");
        }

        if(!PasswordUtil.validatePassword(user.getPassword(), userData.getPassword())) {
            return ResponseEntity.badRequest().body("Incorrect password");
        }

        return ResponseEntity.ok("User logged in successfully");
    }

    public ResponseEntity<String> saveUser(User user) {
        if(!isValidEmail(user.getEmail())) {
            return ResponseEntity.badRequest().body("Invalid email address");
        }

        if(!PasswordUtil.isPasswordValid(user.getPassword())) {
            return ResponseEntity.badRequest().body("Password must be at least 8 characters long");
        }

        // Validate fields via repository
        List<User> existingUsers = userRepository.findByUsernameOrEmail(user.getUsername(), user.getEmail());

        if(!existingUsers.isEmpty()) {
            for(User existingUser : existingUsers) {
                if(Objects.equals(existingUser.getEmail(), user.getEmail())) {
                    return ResponseEntity.badRequest().body("Email is connected to an existing account");
                }

                if(Objects.equals(existingUser.getUsername(), user.getUsername())) {
                    return ResponseEntity.badRequest().body("Username is already taken");
                }
            }
        }

        // Hash user password
        user.setPassword(PasswordUtil.hashPassword(user.getPassword()));
//        userRepository.save(user);

        return ResponseEntity.ok("User saved successfully");
    }

    // Other utility methods
    public static boolean isValidEmail(String email) {
        String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }
}
