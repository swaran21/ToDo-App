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

<<<<<<< HEAD
    @PostMapping()
    public String createTasks(@RequestParam  String title,@RequestParam String taskDate) {
        taskService.createTask(title,LocalDate.parse(taskDate));
        return "redirect:/?date="+taskDate;
=======
    @PostMapping
    public String createTasks(@RequestParam String title, @RequestParam String taskDate) {
        LocalDate date = LocalDate.parse(taskDate);
        taskService.createTask(title, date);
        return "redirect:/?date=" + taskDate;
>>>>>>> test
    }

    @GetMapping
    public String getTasks(@RequestParam(required = false) String date, Model model) {
<<<<<<< HEAD
        LocalDate selectedDate = (date!=null) ? LocalDate.parse(date) : LocalDate.now();
        List<Task> tasks = taskService.getTaskByDate(selectedDate);
        model.addAttribute("tasks",tasks);
=======
        LocalDate selectedDate = (date != null) ? LocalDate.parse(date) : LocalDate.now();
        List<Task> tasks = taskService.getTaskByDate(selectedDate);
        model.addAttribute("tasks", tasks);
>>>>>>> test
        model.addAttribute("totalTasks", tasks.size());
        model.addAttribute("completedTasks", taskService.countCompletedTasksByDate(selectedDate));
        model.addAttribute("pendingTasks", taskService.countPendingTasksByDate(selectedDate));
        model.addAttribute("date", selectedDate);
        return "tasks";
    }

    @GetMapping("/{id}/delete")
<<<<<<< HEAD
    public String deleteTask(@PathVariable Long id,@RequestParam(required = false) String date) {
        taskService.deleteTask(id);
        return "redirect:/?date="+date;
    }

    @GetMapping("/{id}/toggle")
    public String toggleTask(@PathVariable Long id,@RequestParam(required = false) String date) {
        taskService.toggleTask(id);
        return "redirect:/?date="+date;
=======
    public String deleteTask(@PathVariable Long id, @RequestParam(required = false) String date) {
        taskService.deleteTask(id);
        return "redirect:/?date=" + (date != null ? date : LocalDate.now());
    }

    @GetMapping("/{id}/toggle")
    public String toggleTask(@PathVariable Long id, @RequestParam(required = false) String date) {
        taskService.toggleTask(id);
        return "redirect:/?date=" + (date != null ? date : LocalDate.now());
>>>>>>> test
    }

    @GetMapping("/pending")
    public String getPendingTasks(@RequestParam(required = false) String date, Model model) {
<<<<<<< HEAD
        LocalDate selectedDate = (date!=null) ? LocalDate.parse(date) : LocalDate.now();
        List<Task> pendingTasks = taskService.getPendingTasksByDate(selectedDate);
        model.addAttribute("tasks", pendingTasks);
        model.addAttribute("totalTasks", taskService.getAllTasks(selectedDate).size());
=======
        LocalDate selectedDate = (date != null) ? LocalDate.parse(date) : LocalDate.now();
        List<Task> pendingTasks = taskService.getPendingTasksByDate(selectedDate);
        model.addAttribute("tasks", pendingTasks);
        model.addAttribute("totalTasks", taskService.getTaskByDate(selectedDate).size());
>>>>>>> test
        model.addAttribute("completedTasks", taskService.countCompletedTasksByDate(selectedDate));
        model.addAttribute("pendingTasks", pendingTasks.size());
        model.addAttribute("date", selectedDate);
        return "tasks";
    }

    @GetMapping("/completed")
<<<<<<< HEAD
    public String getCompletedTasks(@RequestParam(required = false) String date,Model model) {
        LocalDate selectedDate = (date!=null) ? LocalDate.parse(date) : LocalDate.now();
        List<Task> completedTasks = taskService.getCompletedTasksByDate(selectedDate);
        model.addAttribute("tasks", completedTasks);
        model.addAttribute("totalTasks", taskService.getAllTasks(selectedDate).size());
=======
    public String getCompletedTasks(@RequestParam(required = false) String date, Model model) {
        LocalDate selectedDate = (date != null) ? LocalDate.parse(date) : LocalDate.now();
        List<Task> completedTasks = taskService.getCompletedTasksByDate(selectedDate);
        model.addAttribute("tasks", completedTasks);
        model.addAttribute("totalTasks", taskService.getTaskByDate(selectedDate).size());
>>>>>>> test
        model.addAttribute("completedTasks", completedTasks.size());
        model.addAttribute("pendingTasks", taskService.countPendingTasksByDate(selectedDate));
        model.addAttribute("date", selectedDate);
        return "tasks";
    }
}
