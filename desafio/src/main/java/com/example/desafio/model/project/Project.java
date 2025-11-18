package com.example.desafio.model.project;

import com.example.desafio.model.task.Task;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "project")
public class Project {
    @Id
    private Long id;
    private String projectCreator;
    private String passwordAccess;
    private String titleProject;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    @OneToMany(mappedBy = "project")
    private List<Task> tasks;
}
