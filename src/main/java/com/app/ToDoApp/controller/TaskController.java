package com.app.ToDoApp.controller;

import com.app.ToDoApp.models.Task;
import com.app.ToDoApp.services.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping()
    public String createTasks(@RequestParam  String title,@RequestParam String taskDate) {
        taskService.createTask(title,LocalDate.parse(taskDate));
        return "redirect:/?date="+taskDate;
    }

    @GetMapping
    public String getTasks(@RequestParam(required = false) String date, Model model) {
        LocalDate selectedDate = (date!=null) ? LocalDate.parse(date) : LocalDate.now();
        List<Task> tasks = taskService.getTaskByDate(selectedDate);
        model.addAttribute("tasks",tasks);
        model.addAttribute("totalTasks", tasks.size());
        model.addAttribute("completedTasks", taskService.countCompletedTasksByDate(selectedDate));
        model.addAttribute("pendingTasks", taskService.countPendingTasksByDate(selectedDate));
        model.addAttribute("date", selectedDate);
        return "tasks";
    }


    @GetMapping("/{id}/delete")
    public String deleteTask(@PathVariable Long id,@RequestParam(required = false) String date) {
        taskService.deleteTask(id);
        return "redirect:/?date="+date;
    }

    @GetMapping("/{id}/toggle")
    public String toggleTask(@PathVariable Long id,@RequestParam(required = false) String date) {
        taskService.toggleTask(id);
        return "redirect:/?date="+date;
    }

    @GetMapping("/pending")
    public String getPendingTasks(@RequestParam(required = false) String date, Model model) {
        LocalDate selectedDate = (date!=null) ? LocalDate.parse(date) : LocalDate.now();
        List<Task> pendingTasks = taskService.getPendingTasksByDate(selectedDate);
        model.addAttribute("tasks", pendingTasks);
        model.addAttribute("totalTasks", taskService.getAllTasks(selectedDate).size());
        model.addAttribute("completedTasks", taskService.countCompletedTasksByDate(selectedDate));
        model.addAttribute("pendingTasks", pendingTasks.size());
        model.addAttribute("date", selectedDate);
        return "tasks";
    }

    @GetMapping("/completed")
    public String getCompletedTasks(@RequestParam(required = false) String date,Model model) {
        LocalDate selectedDate = (date!=null) ? LocalDate.parse(date) : LocalDate.now();
        List<Task> completedTasks = taskService.getCompletedTasksByDate(selectedDate);
        model.addAttribute("tasks", completedTasks);
        model.addAttribute("totalTasks", taskService.getAllTasks(selectedDate).size());
        model.addAttribute("completedTasks", completedTasks.size());
        model.addAttribute("pendingTasks", taskService.countPendingTasksByDate(selectedDate));
        model.addAttribute("date", selectedDate);
        return "tasks";
    }


}
