package com.pm.project.mapper;

import com.pm.project.entity.ProjectEntity;
import com.pm.project.model.ProjectModel;
import com.pm.shared.AbstractMapper;
import com.pm.user.entity.UserEntity;
import com.pm.user.mapper.UserMapper;
import com.pm.user.model.UserModel;

import javax.enterprise.context.Dependent;
import java.util.ArrayList;
import java.util.List;

@Dependent
public class ProjectMapper extends AbstractMapper<ProjectModel, ProjectEntity> {

    public ProjectModel deepMapToModel(ProjectEntity entity) {
        final ProjectModel model = new ProjectModel();
        model.setId(entity.getId());
        model.setName(entity.getName());
        model.setStartDate(entity.getStartDate());
        model.setEndDate(entity.getEndDate());
        model.setStatus(entity.getStatus());
        final UserMapper userMapper = new UserMapper();
        final List<UserModel> userModels = new ArrayList<>();
        final List<UserEntity> userEntities = entity.getUsers();
        for (final UserEntity userEntity : userEntities) {
            userModels.add(userMapper.mapToModel(userEntity));
        }
        model.setUsers(userModels);
        return model;
    }

    public ProjectModel mapToModel(ProjectEntity entity) {
        final ProjectModel model = new ProjectModel();
        model.setId(entity.getId());
        model.setName(entity.getName());
        model.setStartDate(entity.getStartDate());
        model.setEndDate(entity.getEndDate());
        model.setStatus(entity.getStatus());
        return model;
    }

    public ProjectEntity deepMapToEntity(ProjectModel model) {
        final ProjectEntity entity = new ProjectEntity();
        entity.setId(model.getId());
        entity.setName(model.getName());
        entity.setStartDate(model.getStartDate());
        entity.setEndDate(model.getEndDate());
        entity.setStatus(model.getStatus());
        final UserMapper userMapper = new UserMapper();
        final List<UserEntity> userEntities = new ArrayList<>();
        final List<UserModel> userModels = model.getUsers();
        for (final UserModel userModel : userModels) {
            userEntities.add(userMapper.mapToEntity(userModel));
        }
        entity.setUsers(userEntities);
        return entity;
    }

    public ProjectEntity mapToEntity(ProjectModel model) {
        final ProjectEntity entity = new ProjectEntity();
        entity.setId(model.getId());
        entity.setName(model.getName());
        entity.setStartDate(model.getStartDate());
        entity.setEndDate(model.getEndDate());
        entity.setStatus(model.getStatus());
        return entity;
    }
}
