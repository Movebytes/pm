package project;

import com.pm.project.model.ProjectModel;
import com.pm.project.model.ProjectStatus;
import com.pm.user.model.UserModel;
import com.pm.user.model.UserStatus;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class ProjectModelIT {
    private static ValidatorFactory vf;
    private static Validator validator;

    @BeforeClass
    public static void init() {
        vf = Validation.buildDefaultValidatorFactory();
        validator = vf.getValidator();
    }

    @AfterClass
    public static void close() {
        vf.close();
    }

    @Test
    public void shouldRaiseNoConstraintViolation() {
        final ProjectModel validProject = new ProjectModel();
        validProject.setName("PPP");
        validProject.setStatus(ProjectStatus.ACTIVE);
        final Set<ConstraintViolation<ProjectModel>> violations = validator.validate(validProject);
        assertEquals(0, violations.size());
    }

    @Test
    public void shouldRaiseConstraintViolationCauseNoActiveProject() {
        final ProjectModel invalidProject = new ProjectModel();
        invalidProject.setName("PPP");
        invalidProject.setStatus(ProjectStatus.ACTIVE);
        final UserModel user = new UserModel();
        user.setStatus(UserStatus.INACTIVE);
        final List<UserModel> userModelList = new ArrayList<>();
        userModelList.add(user);
        invalidProject.setUsers(userModelList);
        final Set<ConstraintViolation<ProjectModel>> violations = validator.validate(invalidProject);
        assertEquals(1, violations.size());
        assertEquals("{com.pm.shared.validator.ActiveUserList.message}", violations.iterator().next().getMessage());
    }
}
