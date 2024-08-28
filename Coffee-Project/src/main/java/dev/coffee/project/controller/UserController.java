package dev.coffee.project.controller;

import dev.coffee.project.dao.UserDAO;
import dev.coffee.project.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private final UserDAO userDAO;

    public UserController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @PostMapping("/register")
    public ResponseEntity<String> userRegistration(@RequestBody User user) {
        return userDAO.createUser(user);
    }

    @PostMapping("/delete")
    public ResponseEntity<User> userDelete(@RequestBody User user) {
        return new ResponseEntity<>(user, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
