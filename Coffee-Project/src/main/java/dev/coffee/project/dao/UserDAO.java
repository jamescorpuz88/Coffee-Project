package dev.coffee.project.dao;

import dev.coffee.project.model.User;
import dev.coffee.project.repository.UserRepository;
import dev.coffee.project.utils.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

@Service
public class UserDAO {

    @Autowired
    private final UserRepository userRepository;

    public UserDAO(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity<String> createUser(User user) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        String email = user.getEmail();

        Pattern emailPattern = Pattern.compile(emailRegex);
        Matcher emailMatcher = emailPattern.matcher(email);

        if(!emailMatcher.matches()) {
            return new ResponseEntity<>("Invalid Email Address", HttpStatus.BAD_REQUEST);
        }

        // Get users with similar username or email
        List<UserRepository.FindUserByUsernameEmail> users = userRepository.findAllProjectedBy();
        for(UserRepository.FindUserByUsernameEmail u : users) {
            if(u.getEmail().equals(user.getEmail())) {
                return new ResponseEntity<>("Email already exists", HttpStatus.BAD_REQUEST);
            }
            if(u.getUsername().equals(user.getUsername())) {
                return new ResponseEntity<>("Username already exists", HttpStatus.BAD_REQUEST);
            }
        }

        if(user.getPassword().length() < 8) {
            return new ResponseEntity<>("Password must be at least 8 characters long", HttpStatus.BAD_REQUEST);
        }

        // Hash Password
        user.setPassword(PasswordUtil.hashPassword(user.getPassword()));
        System.out.println(user);

//        userRepository.save(user);
        return new ResponseEntity<>("User created", HttpStatus.CREATED);
    }

    public ResponseEntity<String> userLogin(User user) {
        UserRepository.FindByUsername userData = userRepository.findByUsername(user.getUsername());

        if(userData != null) {
            if(PasswordUtil.verifyPassword(user.getPassword(), userData.getPassword())) {
                return new ResponseEntity<>("Login successful", HttpStatus.OK);
            }
            return new ResponseEntity<>("Invalid password", HttpStatus.OK);
        }

        return new ResponseEntity<>("User not found", HttpStatus.OK);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
