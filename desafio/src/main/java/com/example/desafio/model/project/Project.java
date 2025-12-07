package com.example.desafio.model.project;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String projectCreator;
    private String passwordAccess;
    private String nameProject;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;



    public Project(){
        this.startDate=LocalDate.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjectCreator() {
        return projectCreator;
    }

    public void setProjectCreator(String projectCreator) {
        this.projectCreator = projectCreator;
    }

    public String getPasswordAccess() {
        return passwordAccess;
    }

    public void setPasswordAccess(String passwordAccess) {
        this.passwordAccess = passwordAccess;
    }

    public String getNameProject() {
        return nameProject;
    }

    public void setNameProject(String nameProject) {
        this.nameProject = nameProject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Project project)) return false;
        return Objects.equals(getId(), project.getId()) && Objects.equals(getProjectCreator(), project.getProjectCreator()) && Objects.equals(getPasswordAccess(), project.getPasswordAccess()) && Objects.equals(getNameProject(), project.getNameProject()) && Objects.equals(getDescription(), project.getDescription()) && Objects.equals(getStartDate(), project.getStartDate()) && Objects.equals(getEndDate(), project.getEndDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getProjectCreator(), getPasswordAccess(), getNameProject(), getDescription(), getStartDate(), getEndDate());
    }
}
