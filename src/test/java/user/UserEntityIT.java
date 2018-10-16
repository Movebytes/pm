package user;

import com.pm.user.entity.UserEntity;
import com.pm.user.model.UserStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import static org.junit.Assert.assertNotNull;

public class UserEntityIT {
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
        final UserEntity userEntity = new UserEntity();
        userEntity.setName("Vasya");
        userEntity.setStatus(UserStatus.INACTIVE);
        userEntity.setSurname("Pipkin");
        tx.begin();
        em.persist(userEntity);
        tx.commit();
        assertNotNull("ID can't be empty", userEntity.getId());
    }
}
