package com.example.task_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


package com.example.taskservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Task {
    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private String ownerId;

    public Task() {}

    public Task(String title, String ownerId) {
        this.title = title;
        this.ownerId = ownerId;
    }

    // getters and setters
}

package com.example.taskservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.taskservice.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
}

package com.example.taskservice.controller;

import org.springframework.web.bind.annotation.*;
        import org.springframework.web.reactive.function.client.WebClient;
import com.example.taskservice.model.Task;
import com.example.taskservice.repository.TaskRepository;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskRepository repo;
    private final WebClient userClient;

    public TaskController(TaskRepository repo) {
        this.repo = repo;
        this.userClient = WebClient.create("http://localhost:8081/users");
    }

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        // Check user exists
        Boolean userExists = userClient.get()
                .uri("/{id}", task.getOwnerId())
                .retrieve()
                .bodyToMono(Object.class)
                .map(u -> true)
                .onErrorReturn(false)
                .block();

        if (!userExists) {
            throw new RuntimeException("User not found: " + task.getOwnerId());
        }

        return repo.save(task);
    }
}

@SpringBootApplication
public class TaskServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskServiceApplication.class, args);
	}

}
