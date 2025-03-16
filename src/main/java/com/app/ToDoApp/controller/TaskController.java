package com.app.ToDoApp.controller;

import com.app.ToDoApp.models.Task;
import com.app.ToDoApp.services.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping()
    public String createTasks(@RequestParam  String title) {
        taskService.createTask(title);
        return "redirect:/";
    }

    @GetMapping
    public String getTasks(Model model) {
        List<Task> tasks = taskService.getAllTasks();
        model.addAttribute("tasks",tasks);
        model.addAttribute("totalTasks", tasks.size());
        model.addAttribute("completedTasks", taskService.countCompletedTasks());
        model.addAttribute("pendingTasks", taskService.countPendingTasks());
        return "tasks";
    }


    @GetMapping("/{id}/delete")
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return "redirect:/";
    }

    @GetMapping("/{id}/toggle")
    public String toggleTask(@PathVariable Long id) {
        taskService.toggleTask(id);
        return "redirect:/";
    }

    @GetMapping("/pending")
    public String getPendingTasks(Model model) {
        List<Task> pendingTasks = taskService.getPendingTasks();
        model.addAttribute("tasks", pendingTasks);
        model.addAttribute("totalTasks", taskService.getAllTasks().size());
        model.addAttribute("completedTasks", taskService.countCompletedTasks());
        model.addAttribute("pendingTasks", pendingTasks.size());
        return "tasks";
    }

    @GetMapping("/completed")
    public String getCompletedTasks(Model model) {
        List<Task> completedTasks = taskService.getCompletedTasks();
        model.addAttribute("tasks", completedTasks);
        model.addAttribute("totalTasks", taskService.getAllTasks().size());
        model.addAttribute("completedTasks", completedTasks.size());
        model.addAttribute("pendingTasks", taskService.countPendingTasks());
        return "tasks";
    }


}
