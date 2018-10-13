package com.pm.project.mapper;

import com.pm.project.entity.ProjectEntity;
import com.pm.project.model.ProjectModel;
import com.pm.shared.AbstractMapper;

import javax.enterprise.context.Dependent;

@Dependent
public class ProjectMapper extends AbstractMapper<ProjectModel, ProjectEntity> {

    public ProjectModel mapToModel(ProjectEntity entity) {
        final ProjectModel model = new ProjectModel();
        model.setId(entity.getId());
        model.setName(entity.getName());
        model.setStartDate(entity.getStartDate());
        model.setEndDate(entity.getEndDate());
        model.setStatus(entity.getStatus());
        return model;
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
