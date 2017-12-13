package ru.job4j.dao.postgres;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.job4j.dao.DAOConfigurationException;
import ru.job4j.dao.DAOFactory;
import ru.job4j.dao.Order;

import org.postgresql.jdbc2.optional.PoolingDataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

/**
 * PostgresFactory.
 *
 * @author Stanislav (376825@mail.ru)
 * @since 04.12.2017
 */
public class PostgresFactory extends DAOFactory {
    private static final Logger LOG = LoggerFactory.getLogger(PostgresFactory.class);
    private final PoolingDataSource source =  new PoolingDataSource();
    private final Properties defaultProperties;
    private boolean initialized = false;

    private PostgresFactory() {
        this.defaultProperties = new Properties();
        this.defaultProperties.setProperty("jdbc.maxConnections", "10");
        this.defaultProperties.setProperty("jdbc.portNumber", "5432");
        this.defaultProperties.setProperty("jdbc.serverName", "localhost");
        this.defaultProperties.setProperty("jdbc.databaseName", "example_two");
        this.defaultProperties.setProperty("jdbc.user", "postgres");
        this.defaultProperties.setProperty("jdbc.password", "123");
    }

    public static PostgresFactory getInstance() {
        return SingletonHolder.HOLDER_INSTANCE;
    }

    /**
     * loadDatabaseSettings - Загрузка настроек подключения к базе данных.
     * Настройки находятся в файле "db.properties". Если файла нет, то
     * загружаются настройки по умолчанию.
     */
    private void loadDatabaseSettings() {
        Properties properties = new Properties(this.defaultProperties);

        try (InputStream inputStream = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("db.properties")) {
            properties.load(inputStream);
        } catch (IOException e) {
            LOG.warn("Warning, could not load the database configuration file", e);
        }

        this.source.setMaxConnections(Integer.valueOf(properties.getProperty("jdbc.maxConnections")));
        this.source.setServerName(properties.getProperty("jdbc.serverName"));
        this.source.setPortNumber(Integer.valueOf(properties.getProperty("jdbc.portNumber")));
        this.source.setDatabaseName(properties.getProperty("jdbc.databaseName"));
        this.source.setUser(properties.getProperty("jdbc.user"));
        this.source.setPassword(properties.getProperty("jdbc.password"));
    }

    /**
     * initializingTables - Если структуры в базе еще нет, то она создается.
     * Скрипты находятся в файле "script.sql".
     */
    private void initializingTables() throws DAOConfigurationException {
        Properties properties = new Properties();

        try (Connection con = this.source.getConnection();
             InputStream inputStream = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("script.sql")) {

            properties.load(inputStream);

            String script = properties.getProperty("request");

            if (script != null && !script.isEmpty()) {
                PreparedStatement statement = con.prepareStatement(script);
                statement.execute();
            }

            script = properties.getProperty("comments");

            if (script != null && !script.isEmpty()) {
                PreparedStatement statement = con.prepareStatement(script);
                statement.execute();
            }
        } catch (IOException e) {
            LOG.warn("When the tables were initialized, the script file was not found", e);
        } catch (SQLException e) {
            throw new DAOConfigurationException("Error in script", e);
        }
    }

    /**
     * getConnection - Получить connection с пула.
     */
    public Connection getConnection() throws SQLException, DAOConfigurationException {
        if (!initialized) {
            loadDatabaseSettings();
            initializingTables();
            this.initialized = true;
        }

        return this.source.getConnection();
    }

    /**
     * closeAllConnection - Закрывает пул соединений.
     */
    public void closeAllConnection() {
        this.source.close();
    }

    @Override
    public Order getOrderDAO() {
        return new PostgresOrder();
    }

    /**
     * SingletonHolder - Initialization-on-demand holder idiom.
     */
    private static class SingletonHolder {
        static final PostgresFactory HOLDER_INSTANCE = new PostgresFactory();
    }
}