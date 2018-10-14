package com.pm.user.mapper;

import com.pm.project.entity.ProjectEntity;
import com.pm.project.mapper.ProjectMapper;
import com.pm.project.model.ProjectModel;
import com.pm.shared.AbstractMapper;
import com.pm.user.entity.UserEntity;
import com.pm.user.model.UserModel;

import javax.enterprise.context.Dependent;
import java.util.ArrayList;
import java.util.List;

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

    public UserModel deepMapToModel(UserEntity entity) {
        final UserModel model = new UserModel();
        model.setId(entity.getId());
        model.setName(entity.getName());
        model.setSurname(entity.getSurname());
        model.setStatus(entity.getStatus());
        final ProjectMapper projectMapper = new ProjectMapper();
        final List<ProjectEntity> projectEntities = entity.getProjects();
        final List<ProjectModel> projectModels = new ArrayList<>();
        for (final ProjectEntity projectEntity : projectEntities) {
            projectModels.add(projectMapper.mapToModel(projectEntity));
        }
        model.setProjects(projectModels);
        return model;
    }

    public UserEntity deepMapToEntity(UserModel model) {
        final UserEntity entity = new UserEntity();
        entity.setId(model.getId());
        entity.setName(model.getName());
        entity.setSurname(model.getSurname());
        entity.setStatus(model.getStatus());
        final ProjectMapper projectMapper = new ProjectMapper();
        final List<ProjectModel> projectModels = model.getProjects();
        final List<ProjectEntity> projectEntities = new ArrayList<>();
        for (final ProjectModel projectModel : projectModels) {
            projectEntities.add(projectMapper.mapToEntity(projectModel));
        }
        entity.setProjects(projectEntities);
        return entity;
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
