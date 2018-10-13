package com.pm.user.model;

import javax.validation.constraints.*;

public class UserModel {
    private final String PATTERN = "^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$";

    public enum Status {
        INACTIVE,
        ACTIVE,
        DELETED
    }

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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
