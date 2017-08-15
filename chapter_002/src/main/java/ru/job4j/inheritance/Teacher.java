package ru.job4j.inheritance;

/**
 * Teacher - учитель.
 *
 * @author Stanislav (376825@mail.ru)
 * @version $Id$
 * @since 0.1
 */
public class Teacher extends Profession {
    /**
     * массив предметов преподования.
     */
    private SubjectStudy[] subjectStudy;

    /**
     * Constructor
     * @param name - String
     * @param subjectStudy - SubjectStudy[]
     */
    public Teacher(String name, SubjectStudy[] subjectStudy) {
        super(name);
        this.subjectStudy = subjectStudy;
    }

    /**
     * teach.
     * @param classRoom - ClassRoom
     */
    public void teach(ClassRoom classRoom) {
        System.out.println("Учитель " + this.getName() + " проводит урок в классе " + classRoom.getName());
    }
}