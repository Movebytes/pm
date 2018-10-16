package user;

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

public class UserModelIT {
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
        final UserModel validUser = new UserModel();
        validUser.setName("Vasya");
        validUser.setSurname("Pupkin");
        validUser.setStatus(UserStatus.ACTIVE);
        final Set<ConstraintViolation<UserModel>> violations = validator.validate(validUser);
        assertEquals(0, violations.size());
    }

    @Test
    public void shouldRaiseConstraintViolationCauseNoActiveProject() {
        final UserModel invalidUser = new UserModel();
        invalidUser.setName("Vasya");
        invalidUser.setSurname("Pupkin");
        invalidUser.setStatus(UserStatus.ACTIVE);
        final ProjectModel project = new ProjectModel();
        project.setStatus(ProjectStatus.CREATED);
        final List<ProjectModel> projectModelList = new ArrayList<>();
        projectModelList.add(project);
        invalidUser.setProjects(projectModelList);
        final Set<ConstraintViolation<UserModel>> violations = validator.validate(invalidUser);
        assertEquals(1, violations.size());
        assertEquals("{com.pm.shared.validator.ActiveProjectList.message}", violations.iterator().next().getMessage());
    }
}
