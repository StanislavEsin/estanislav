package ru.job4j.vacancy.dao;

import org.postgresql.jdbc2.optional.PoolingDataSource;
import ru.job4j.vacancy.utils.PropertiesHolder;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.Queue;

/**
 * DataSource.
 *
 * @author Stanislav (376825@mail.ru)
 * @since 02.01.2018
 */
public enum DataSource {
    INSTANCE;
    private volatile boolean initialized = false;
    private boolean initializedTables = false;
    private final PoolingDataSource source =  new PoolingDataSource();

    public synchronized void init() {
        if (!initialized) {
            initPollConnection();
            initialized = true;
        }
    }

    private void initPollConnection() {
        PropertiesHolder propertiesHolder = PropertiesHolder.INSTANCE;
        this.source.setMaxConnections(propertiesHolder.getMaxConnections());
        this.source.setServerName(propertiesHolder.getServerName());
        this.source.setPortNumber(propertiesHolder.getPortNumber());
        this.source.setDatabaseName(propertiesHolder.getDatabaseName());
        this.source.setUser(propertiesHolder.getUser());
        this.source.setPassword(propertiesHolder.getPassword());
        this.source.setInitialConnections(Math.max(propertiesHolder.getMaxConnections() / 2, 1));
    }

    private void initTables() throws SQLException {
        PropertiesHolder propertiesHolder = PropertiesHolder.INSTANCE;
        Queue<String> scripts = propertiesHolder.getScripts();

        while (scripts.size() != 0) {
            String script = scripts.poll();

            try (Connection con = this.source.getConnection();
                 Statement st = con.createStatement()) {
                st.execute(script);
            } catch (SQLException e) {
                throw new SQLException("Error in script.", e);
            }
        }
    }

    public Connection getConnection() throws SQLException {
        if (!this.initializedTables) {
            synchronized (DataSource.class) {
                if (!this.initializedTables) {
                    initTables();
                    this.initializedTables = true;
                }
            }
        }

        return this.source.getConnection();
    }

    public void closeAllConnection() {
        this.source.close();
    }
}