package com.pm.project.model;

import com.pm.shared.validator.ActiveUserList;
import com.pm.user.model.UserModel;

import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProjectModel {
    private Integer id;

    @NotNull
    @Pattern(regexp = "^(([|\"',. -:])|([a-zA-Z ]))*$")
    @Size(min = 2, max = 255)
    private String name;

    private Date startDate;

    private Date endDate;

    @NotNull
    private Integer status;

    @ActiveUserList
    private List<UserModel> users = new ArrayList<>();

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<UserModel> getUsers() {
        return users;
    }

    public void setUsers(List<UserModel> users) {
        this.users = users;
    }
}
