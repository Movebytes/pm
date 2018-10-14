package com.pm.shared.validator;

import com.google.auto.service.AutoService;
import com.pm.user.model.UserModel;
import com.pm.user.model.UserStatus;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

@AutoService(ConstraintValidator.class)
public class ActiveUserValidator implements ConstraintValidator<ActiveUser, List<UserModel>> {

    public void initialize(ActiveUser activeProject) {
    }

    public boolean isValid(List<UserModel> users, ConstraintValidatorContext context) {
        if (users == null || users.size() == 0) {
            return true;
        }
        for (final UserModel user : users) {
            if (user.getStatus() != UserStatus.ACTIVE) {
                return false;
            }
        }
        return true;
    }
}
