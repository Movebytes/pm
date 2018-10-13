package com.pm.user.entity;

import com.pm.user.model.UserModel;

import javax.persistence.*;

@Entity
@Table(name = "pm_user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 50)
    private String surname;

    @Column(nullable = false)
    private UserModel.Status status;

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

    public UserModel.Status getStatus() {
        return status;
    }

    public void setStatus(UserModel.Status status) {
        this.status = status;
    }
}
