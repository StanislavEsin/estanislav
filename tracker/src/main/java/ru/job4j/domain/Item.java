package ru.job4j.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

/**
 * Item - заявка.
 *
 * @author Stanislav (376825@mail.ru)
 * @version $Id$
 * @since 1.0
 */
public class Item implements Comparable {
    /**
     * генератор для id.
     */
    private static int nextId = 1;

    /**
     */
    private String id;
    /**
     */
    private String name;
    /**
     */
    private String desc;
    /**
     */
    private long created;
    /**
     */
    private ArrayList<String> comments;

    /**
     * Item.
     * @param name - String
     * @param desc - String
     * @param created - long
     */
    public Item(String name, String desc, long created) {
        this.id = String.valueOf(nextId++);
        this.name = name;
        this.desc = desc;
        this.created = created;
        this.comments = new ArrayList<>();
    }

    /**
     * Item.
     * @param id - String
     * @param name - String
     * @param desc - String
     * @param created - long
     */
    public Item(String id, String name, String desc, long created) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.created = created;
        this.comments = new ArrayList<>();
    }

    /**
     * getId.
     * @return return - String
     */
    public String getId() {
        return id;
    }

    /**
     * getName.
     * @return return - String
     */
    public String getName() {
        return name;
    }

    /**
     * getDesc.
     * @return return - String
     */
    public String getDesc() {
        return desc;
    }

    /**
     * getCreated.
     * @return return - long
     */
    public long getCreated() {
        return created;
    }

    /**
     * getComments.
     * @return ArrayList<String>.
     */
    public ArrayList<String> getComments() {
        return this.comments;
    }

    /**
     * getComments.
     * @param number - int.
     * @return String.
     */
    public String getComments(int number) {
        return this.comments.get(number);
    }

    /**
     * setName.
     * @param name - String
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * setDesc.
     * @param desc - String
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * setComments.
     * @param comments - String
     */
    public void setComments(String comments) {
        this.comments.add(comments);
    }

    /**
     * setComments.
     * @param comments - ArrayList<String>
     */
    public void setComments(ArrayList<String> comments) {
        this.comments = comments;
    }

    /**
     * setId.
     * @param id - String
     */
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int compareTo(Object o) {
        return Integer.compare(Integer.valueOf(this.getId()), Integer.valueOf(((Item) o).getId()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Item item = (Item) o;
        return created == item.created
                && Objects.equals(id, item.id)
                && Objects.equals(name, item.name)
                && Objects.equals(desc, item.desc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, desc, created);
    }

    @Override
    public String toString() {
        return "id: " + this.getId()
                + ", name: " + this.getName()
                + ", description: " + this.getDesc()
                + ", created: " + new Date(this.getCreated());
    }
}