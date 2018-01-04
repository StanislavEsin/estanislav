package ru.job4j.vacancy.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

import java.util.LinkedList;
import java.util.Properties;
import java.util.Queue;

/**
 * PropertiesHolder.
 *
 * @author Stanislav (376825@mail.ru)
 * @since 28.12.2017
 */
public final class PropertiesHolder {
    private static final Logger LOG = LoggerFactory.getLogger(JobHandler.class);
    private volatile static PropertiesHolder instance;
    private Properties defaultProp;
    private int portNumber;
    private String serverName;
    private String databaseName;
    private String user;
    private String password;
    private int maxConnections;
    private long runTimeSchedule;
    private Queue<String> scripts = new LinkedList<>();

    public PropertiesHolder() {
        setDefaultProperties();
        loadProperties();
        loadInitializingScripts();
    }

    public static PropertiesHolder getInstance() {
        if (instance == null) {
            synchronized (PropertiesHolder.class) {
                if (instance == null) {
                    instance = new PropertiesHolder();
                }
            }
        }

        return instance;
    }

    private void setDefaultProperties() {
        this.defaultProp = new Properties();
        defaultProp.setProperty("jdbc.portNumber", "5432");
        defaultProp.setProperty("jdbc.serverName", "localhost");
        defaultProp.setProperty("jdbc.databaseName", "vacancies");
        defaultProp.setProperty("jdbc.user", "postgres");
        defaultProp.setProperty("jdbc.password", "123");
        defaultProp.setProperty("jdbc.maxConnections", "10");
        defaultProp.setProperty("program_settings.run_time_schedule", "86400000");
    }

    private void loadProperties() {
        Properties prop = new Properties(this.defaultProp);

        try (InputStream inputStream = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("application.properties")) {
            prop.load(inputStream);
        } catch (NullPointerException e) {
            LOG.warn("Property file not found.", e);
        } catch (IOException e) {
            LOG.error("Problems with reading the settings file.", e);
        }

        this.portNumber = Integer.valueOf(prop.getProperty("jdbc.portNumber"));
        this.serverName = prop.getProperty("jdbc.serverName");
        this.databaseName = prop.getProperty("jdbc.databaseName");
        this.user = prop.getProperty("jdbc.user");
        this.password = prop.getProperty("jdbc.password");
        this.maxConnections = Integer.valueOf(prop.getProperty("jdbc.maxConnections"));
        this.runTimeSchedule = Long.valueOf(prop.getProperty("program_settings.run_time_schedule"));
    }

    private void loadInitializingScripts() {
        scripts.offer(new StringBuilder()
                .append("CREATE TABLE IF NOT EXISTS public.vacancies(")
                .append("id bigserial PRIMARY KEY, name text NOT NULL, text text, link text NOT NULL, ")
                .append("time_create timestamp NOT NULL,CONSTRAINT unique_vacancy UNIQUE (name, link))").toString());
    }

    public int getPortNumber() {
        return portNumber;
    }

    public String getServerName() {
        return serverName;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public int getMaxConnections() {
        return maxConnections;
    }

    public long getRunTimeSchedule() {
        return runTimeSchedule;
    }

    public Queue<String> getScripts() {
        return scripts;
    }
}