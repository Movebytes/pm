package com.pm.shared.validator;

import com.pm.project.model.ProjectModel;
import com.pm.project.model.ProjectStatus;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class ActiveProjectValidator implements ConstraintValidator<ActiveProject, List<ProjectModel>> {

    public void initialize(ActiveProject activeProject) {
    }

    public boolean isValid(List<ProjectModel> projects, ConstraintValidatorContext context) {
        if (projects == null || projects.size() == 0) {
            return true;
        }
        for (final ProjectModel project : projects) {
            if (project.getStatus() != ProjectStatus.ACTIVE) {
                return false;
            }
        }
        return true;
    }
}
