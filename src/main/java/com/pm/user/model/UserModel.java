package com.pm.user.model;

import com.pm.project.model.ProjectModel;
import com.pm.shared.validator.ActiveProject;

import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

public class UserModel {
    private final String PATTERN = "^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$";

    private Integer id;

    @NotNull
    @Size(min = 3, max = 50)
    @Pattern(regexp = PATTERN)
    private String name;

    @NotNull
    @Size(min = 3, max = 50)
    @Pattern(regexp = PATTERN)
    private String surname;

    @NotNull
    private Integer status;

    @Size(min = 0, max = 1)
    @ActiveProject
    private List<ProjectModel> projects = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<ProjectModel> getProjects() {
        return projects;
    }

    public void setProjects(List<ProjectModel> projects) {
        this.projects = projects;
    }
}
