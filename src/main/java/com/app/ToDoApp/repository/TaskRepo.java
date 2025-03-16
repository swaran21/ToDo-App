package com.app.ToDoApp.repository;

import com.app.ToDoApp.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TaskRepo extends JpaRepository<Task, Long> {
    long countByCompletedAndTaskDate(boolean isCompleted, LocalDate date);
<<<<<<< HEAD
    long countByCompleted(boolean isCompleted);
    List<Task> findByCompletedFalseAndTaskDate(LocalDate taskDate);
    List<Task> findByCompletedTrueAndTaskDate(LocalDate taskDate);
    List<Task> findByTaskDate(LocalDate taskDate);

=======
    List<Task> findByCompletedFalseAndTaskDate(LocalDate taskDate);
    List<Task> findByCompletedTrueAndTaskDate(LocalDate taskDate);
    List<Task> findByTaskDate(LocalDate taskDate);
>>>>>>> test
}
