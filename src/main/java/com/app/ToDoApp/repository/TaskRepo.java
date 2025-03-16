package com.app.ToDoApp.repository;

import com.app.ToDoApp.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepo extends JpaRepository<Task, Long> {
    long countByCompletedFalse();
    long countByCompletedTrue();
    List<Task> findByCompletedFalse();
    List<Task> findByCompletedTrue();

}
