package com.pm.project.model;

import javax.validation.constraints.*;
import java.util.Date;

public class ProjectModel {
    public enum Status {
        CREATED,
        ACTIVE,
        FINISHED
    }

    private Integer id;

    @NotNull
    @Pattern(regexp = "^(([|\"',. -:])|([a-zA-Z ]))*$")
    @Size(min = 2, max = 255)
    private String name;

    @FutureOrPresent
    private Date startDate;

    @Future
    private Date endDate;

    @NotNull
    private Status status;

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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
