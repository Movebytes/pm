package core;

import liquibase.Liquibase;
import liquibase.changelog.DatabaseChangeLog;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.hibernate.Session;
import org.hibernate.jdbc.Work;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;

public class MigrationIT {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("test-unit");

    private static String CONTEXT = "test";
    private static String CHANGELOG = "liquibase/db.changelog-master.xml";

    private EntityManager em;

    @Before
    public void initEntityManagerAndPrepareDB() throws LiquibaseException {
        em = emf.createEntityManager();
        Session session = em.unwrap(Session.class);
        session.doWork(new Work() {
            @Override
            public void execute(Connection connection) throws SQLException {
                Liquibase liquibase;
                Database database;
                try {
                    database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));
                    ClassLoader classLoader = getClass().getClassLoader();
                    File file = new File(classLoader.getResource(CHANGELOG).getFile());
                    liquibase = new Liquibase(new DatabaseChangeLog(file.getAbsolutePath()), new ClassLoaderResourceAccessor(), database);
                    liquibase.update(CONTEXT);
                } catch (LiquibaseException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Test
    public void testDBMigration() {
        // todo
    }
}
