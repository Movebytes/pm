package com.pm.user.mapper;

import com.pm.shared.AbstractMapper;
import com.pm.user.entity.UserEntity;
import com.pm.user.model.UserModel;

import javax.enterprise.context.Dependent;

@Dependent
public class UserMapper extends AbstractMapper<UserModel, UserEntity> {

    public UserModel mapToModel(UserEntity entity) {
        final UserModel model = new UserModel();
        model.setId(entity.getId());
        model.setName(entity.getName());
        model.setSurname(entity.getSurname());
        model.setStatus(entity.getStatus());
        return model;
    }

    public UserEntity mapToEntity(UserModel model) {
        final UserEntity entity = new UserEntity();
        entity.setId(model.getId());
        entity.setName(model.getName());
        entity.setSurname(model.getSurname());
        entity.setStatus(model.getStatus());
        return entity;
    }
}
