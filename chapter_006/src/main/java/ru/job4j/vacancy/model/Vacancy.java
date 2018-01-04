package ru.job4j.vacancy.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Vacancy.
 *
 * @author Stanislav (376825@mail.ru)
 * @since 11.12.2017
 */
public class Vacancy implements Serializable {
    private static final long serialVersionUID = -317506232086980461L;
    private long id;
    private LocalDateTime timeCreate;
    private String name;
    private String text;
    private String link;

    public LocalDateTime getTimeCreate() {
        return timeCreate;
    }

    public void setTimeCreate(LocalDateTime timeCreate) {
        this.timeCreate = timeCreate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Vacancy vacancy = (Vacancy) o;
        return id == vacancy.id
                && Objects.equals(link, vacancy.link);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, link);
    }

    @Override
    public String toString() {
        return "Vacancy{" + "id=" + id
                + ", timeCreate=" + timeCreate
                + ", name='" + name + '\''
                + ", text='" + text + '\''
                + ", link='" + link + '\'' + '}';
    }
}