package dev.coffee.project.repository;

import dev.coffee.project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    interface FindUserByUsernameEmail {
        String getUsername();
        String getEmail();
    }

    interface FindByUsername {
        String getPassword();
    }

    List<FindUserByUsernameEmail> findAllProjectedBy();
    FindByUsername findByUsername(String username);
}