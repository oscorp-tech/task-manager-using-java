package com.example.taskmaster;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.jpa.repository.JpaRepository;

@SpringBootApplication
public class TaskMasterApplication {
    public static void main(String[] args) {
        SpringApplication.run(TaskMasterApplication.class, args);
    }
}

interface TaskRepository extends JpaRepository<Task, Long> {}

@Controller
@RequestMapping("/")
class TaskController {
    private final TaskRepository repository;
    public TaskController(TaskRepository repository) { this.repository = repository; }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("tasks", repository.findAll());
        return "index";
    }

    @PostMapping("/add")
    public String addTask(@RequestParam String description) {
        Task task = new Task();
        task.setDescription(description);
        repository.save(task);
        return "redirect:/";
    }
}