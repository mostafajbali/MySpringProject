package com.example.user_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
package com.example.userservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class User {
    @Id
    private String id;
    private String username;
    private String email;

    public User() {}

    public User(String id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }

    // getters and setters
}

package com.example.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.userservice.model.User;

public interface UserRepository extends JpaRepository<User, String> {
}

package com.example.userservice.controller;

import org.springframework.web.bind.annotation.*;
        import com.example.userservice.model.User;
import com.example.userservice.repository.UserRepository;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserRepository repo;

    public UserController(UserRepository repo) {
        this.repo = repo;
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return repo.save(user);
    }

    @GetMapping("/{id}")
    public Optional<User> getUser(@PathVariable String id) {
        return repo.findById(id);
    }
}

@SpringBootApplication
public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);

	}

}
