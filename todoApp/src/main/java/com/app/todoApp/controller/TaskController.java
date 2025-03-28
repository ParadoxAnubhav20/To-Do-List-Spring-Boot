package com.app.todoApp.controller;

import com.app.todoApp.models.Task;
import com.app.todoApp.services.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public String getTasks(Model model) {
        List<Task> tasks = taskService.getAllTasks();
        model.addAttribute("tasks", tasks);
        return "tasks";
    }

    @PostMapping("/create")
    public String createTask(@RequestParam String title,
                             @RequestParam String priority,
                             @RequestParam(required = false) LocalDate dueDate) {
        taskService.createTask(title, priority, dueDate);
        return "redirect:/tasks";
    }

    @GetMapping("/{id}/delete")
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return "redirect:/tasks";
    }

    @GetMapping("/deleteAll")
    public String deleteAllTasks() {
        taskService.deleteAllTasks();
        return "redirect:/tasks";
    }

    @GetMapping("/{id}/toggle")
    public String toggleTask(@PathVariable Long id) {
        taskService.toggleTask(id);
        return "redirect:/tasks";
    }

    @PostMapping("/{id}/edit")
    public String editTask(@PathVariable Long id,
                           @RequestParam String title,
                           @RequestParam String priority,
                           @RequestParam(required = false) LocalDate dueDate) {
        taskService.editTask(id, title, priority, dueDate);
        return "redirect:/tasks";
    }
}
