package com.pm.setup;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import liquibase.resource.ResourceAccessor;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

@Startup
@Singleton
@TransactionManagement(TransactionManagementType.BEAN)
public class Initializer {
    private static final String STAGE = "development";

    @Resource
    private DataSource dataSource;

    @PostConstruct
    protected void bootstrap() {
        ResourceAccessor resourceAccessor = new ClassLoaderResourceAccessor(getClass().getClassLoader());
        try (Connection connection = dataSource.getConnection()) {
            JdbcConnection jdbcConnection = new JdbcConnection(connection);
            Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(jdbcConnection);

            Properties properties = new Properties();
            properties.load(new FileInputStream("src/main/resources/db.properties"));

            Liquibase liquibase = new Liquibase(properties.getProperty("change.log.file"), resourceAccessor, database);
            liquibase.update(STAGE);
        }
        catch (IOException e) {

        }
        catch (SQLException | LiquibaseException e) {

        }
    }
}
