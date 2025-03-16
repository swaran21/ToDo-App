package com.app.ToDoApp.services;

import com.app.ToDoApp.models.Task;
import com.app.ToDoApp.repository.TaskRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {
    private final TaskRepo taskRepo;

    public TaskService(TaskRepo taskRepo) {
        this.taskRepo = taskRepo;
    }

    public List<Task> getAllTasks() {
        return taskRepo.findAll();
    }

    public void createTask(String title) {
        Task task = new Task();
        task.setTitle(title);
        task.setCompleted(false);
        taskRepo.save(task);
    }

    public void deleteTask(Long id) {
        taskRepo.deleteById(id);
    }

    public void toggleTask(Long id) {
        Task task = taskRepo.findById(id).orElseThrow(()->new IllegalArgumentException("Invalid Task"));
        task.setCompleted(!task.isCompleted());
        taskRepo.save(task);
    }

    public long countPendingTasks() {
        return taskRepo.countByCompletedFalse();
    }

    public long countCompletedTasks() {
        return taskRepo.countByCompletedTrue();
    }

    public List<Task> getPendingTasks() {
        return taskRepo.findByCompletedFalse();
    }

    public List<Task> getCompletedTasks() {
        return taskRepo.findByCompletedTrue();
    }
}
