package com.pm.shared.validator;

import com.pm.user.model.UserModel;
import com.pm.user.model.UserStatus;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class ActiveUserValidator implements ConstraintValidator<ActiveUserList, List<UserModel>> {

    public void initialize(ActiveUserList activeProject) {
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
