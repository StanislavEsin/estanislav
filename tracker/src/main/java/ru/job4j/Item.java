package ru.job4j;

import java.util.Random;

/**
 * Item - заявка.
 *
 * @author Stanislav (376825@mail.ru)
 * @version $Id$
 * @since 1.0
 */
public class Item {
    /**
     * генератор для id.
     */
    private static final Random RN = new Random();

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
    private String[] comments;

    /**
     * @see
     * @param name - String
     * @param desc - String
     * @param created - long
     */
    public Item(String name, String desc, long created) {
        this.id = this.generateId();
        this.name = name;
        this.desc = desc;
        this.created = created;
    }

    /**
     * @see
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
     * @return return - String[]
     */
    public String[] getComments() {
        return comments;
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
     * @param comments - String[]
     */
    public void setComments(String[] comments) {
        this.comments = comments;
    }

    /**
     * generateId - генерирует рэндомный Id.
     * @return return - String
     */
    private String generateId() {
        return String.valueOf(System.currentTimeMillis() + RN.nextInt());
    }
}