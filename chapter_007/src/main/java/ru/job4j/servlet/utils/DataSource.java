package ru.job4j.servlet.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.tomcat.jdbc.pool.PoolProperties;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * DataSource.
 *
 * @author Stanislav (376825@mail.ru)
 * @since 02.01.2018
 */
public enum DataSource {
    INSTANCE;
    private static final Logger LOG = LoggerFactory.getLogger(DataSource.class);
    private boolean initialized = false;
    private org.apache.tomcat.jdbc.pool.DataSource dataSource;

    public void init() {
        if (!initialized) {
            PoolProperties p = new PoolProperties();
            p.setUrl("jdbc:postgresql://127.0.0.1:5432/servlet");
            p.setDriverClassName("org.postgresql.Driver");
            p.setUsername("postgres");
            p.setPassword("123");
            p.setJmxEnabled(true);
            p.setTestWhileIdle(false);
            p.setTestOnBorrow(true);
            p.setValidationQuery("SELECT 1");
            p.setTestOnReturn(false);
            p.setValidationInterval(30000);
            p.setTimeBetweenEvictionRunsMillis(30000);
            p.setMaxActive(100);
            p.setInitialSize(5);
            p.setMaxWait(10000);
            p.setRemoveAbandonedTimeout(60);
            p.setMinEvictableIdleTimeMillis(30000);
            p.setMinIdle(10);
            p.setLogAbandoned(true);
            p.setRemoveAbandoned(true);
            p.setJdbcInterceptors("org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;"
                    + "org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer");
            dataSource = new org.apache.tomcat.jdbc.pool.DataSource();
            dataSource.setPoolProperties(p);

            try {
                Connection con = getConnection();
                con.close();
            } catch (SQLException e) {
                LOG.error("An error occurred in the configuration of the connection to the database.", e);
                throw new DataSourceConfigurationException("An error occurred in the configuration "
                        + "of the connection to the database. ", e);
            }

            initialized = true;
        }
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public void closePoolConnection() {
        dataSource.close();
    }
}