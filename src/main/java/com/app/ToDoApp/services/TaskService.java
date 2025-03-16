package com.app.ToDoApp.services;

import com.app.ToDoApp.models.Task;
import com.app.ToDoApp.repository.TaskRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service
public class TaskService {
    private final TaskRepo taskRepo;

    public TaskService(TaskRepo taskRepo) {
        this.taskRepo = taskRepo;
    }

    public List<Task> getAllTasks(LocalDate selectedDate) {
        return taskRepo.findByTaskDate(selectedDate);
    }

    public void createTask(String title,LocalDate taskDate) {
        Task task = new Task();
        task.setTitle(title);
        task.setCompleted(false);
        task.setTaskDate(taskDate);
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

    public long countPendingTasksByDate(LocalDate date) {
        return taskRepo.countByCompletedAndTaskDate(false,date);
    }

    public long countCompletedTasksByDate(LocalDate date) {
        return taskRepo.countByCompletedAndTaskDate(true, date);
    }

    public List<Task> getPendingTasksByDate(LocalDate date) {
        return taskRepo.findByCompletedFalseAndTaskDate(date);
    }

    public List<Task> getCompletedTasksByDate(LocalDate date) {
        return taskRepo.findByCompletedTrueAndTaskDate(date);
    }

    public List<Task> getTaskByDate(LocalDate taskDate) {
        return taskRepo.findByTaskDate(taskDate);
    }

}
