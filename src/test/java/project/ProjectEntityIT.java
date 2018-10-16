package project;

import com.pm.project.entity.ProjectEntity;
import com.pm.project.model.ProjectStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import static org.junit.Assert.assertNotNull;

public class ProjectEntityIT {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("test-unit");

    private EntityManager em;
    private EntityTransaction tx;

    @Before
    public void initEntityManagerAndPrepareDB() throws Exception {
        em = emf.createEntityManager();
        tx = em.getTransaction();
    }

    @After
    public void closeEntityManager() throws Exception {
        if (em != null) {
            em.close();
        }
    }

    @Test
    public void shouldCreateUser() throws Exception {
        final ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setName("PPP");
        projectEntity.setStatus(ProjectStatus.ACTIVE);
        tx.begin();
        em.persist(projectEntity);
        tx.commit();
        assertNotNull("ID can't be empty", projectEntity.getId());
    }
}
