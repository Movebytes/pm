package com.pm.core;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.NoSuchElementException;

@Startup
@Singleton
@TransactionManagement(TransactionManagementType.BEAN)
public class Initializer {
    final private static String CONTEXT = "development";
    final private static String CHANGELOG = "liquibase/db.changelog-master.xml";

    @Resource
    DataSource dataSource;

    @PostConstruct
    private void migrate() {
        Liquibase liquibase;
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));
            liquibase = new Liquibase(CHANGELOG, new ClassLoaderResourceAccessor(), database);
            liquibase.update(CONTEXT);
        }
        catch (SQLException | LiquibaseException e) {
            e.printStackTrace();
            throw new NoSuchElementException(e.getMessage());
        }
        finally {
            if (connection != null) {
                try {
                    connection.rollback();
                    connection.close();
                }
                catch (SQLException e) {
                    // do nothing
                }
            }
        }
    }

}
