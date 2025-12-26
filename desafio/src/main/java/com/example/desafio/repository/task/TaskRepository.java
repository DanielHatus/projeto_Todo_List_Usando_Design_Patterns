package com.example.desafio.repository.task;

import com.example.desafio.enums.Priority;
import com.example.desafio.enums.Status;
import com.example.desafio.model.task.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task,Long>{
    List<Task> findByStatusAndPriorityAndProjectId(Status status, Priority priority, Long id);
}
