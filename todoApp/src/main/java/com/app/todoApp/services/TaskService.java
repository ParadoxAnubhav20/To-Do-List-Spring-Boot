package com.app.todoApp.services;

import com.app.todoApp.models.Task;
import com.app.todoApp.respository.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public void createTask(String title, String priority, LocalDate dueDate) {
        Task task = new Task();
        task.setTitle(title);
        task.setPriority(priority);
        task.setDueDate(dueDate);
        task.setCompleted(false);
        taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public void deleteAllTasks() {
        taskRepository.deleteAll();
    }

    public void toggleTask(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Task Id"));
        task.setCompleted(!task.isCompleted());
        taskRepository.save(task);
    }

    public void editTask(Long id, String title, String priority, LocalDate dueDate) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Task Id"));
        task.setTitle(title);
        task.setPriority(priority);
        task.setDueDate(dueDate);
        taskRepository.save(task);
    }
}
