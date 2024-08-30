package dev.coffee.project.repository;

import dev.coffee.project.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    @Query("SELECT user FROM User user WHERE user.username = :username OR user.email = :email")
    List<User> findByUsernameOrEmail(@Param("username") String username, @Param("email") String email);
}
