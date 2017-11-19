package ru.job4j.aquarium;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Fish.
 *
 * @author Stanislav (376825@mail.ru)
 * @since 18.11.2017
 */
public class Fish implements Comparable {
    /**
     */
    private final static ReentrantLock LOCK = new ReentrantLock();
    /**
     * следующий номер рыбки.
     */
    private static long nextNumber;
    /**
     * имя рыбки, можно просто использовать номер.
     */
    private final String name;
    /**
     * пол рыбки.
     */
    private final Gender gender;
    /**
     * время рождения (количество миллисекунд, прошедших с момента полуночи 1 января 1970).
     */
    private final long dateOfBirth;
    /**
     * время жизни в миллисекундах.
     */
    private final int lifetime;

    /**
     * Constructor.
     * @param name - String.
     * @param gender - Gander.
     * @param lifetime - int.
     */
    private Fish(String name, Gender gender, int lifetime) {
        this.name = name;
        this.gender = gender;
        this.lifetime = lifetime;
        this.dateOfBirth = new Date().getTime();
    }

    /**
     * giveBirthToFish - родить рыбку. Время жизни рэндомное.
     * @param gender - Gander.
     * @return Fish.
     */
    public static Fish giveBirthToFish(Gender gender) {
        Fish newFish = new Fish(String.format("№ %s", Fish.getNextNumber()), gender, new Random().nextInt(20001));

        System.out.println(String.format("Родилась рыбка %s", newFish.getName()));

        return newFish;
    }

    /**
     * conceiveFish - зачать рыбку. Рэндомное, нууу не получилось, печалька. Пол рэндомный.
     * @return Fish.
     */
    public static Fish conceiveFish(Fish male, Fish female) {
        Fish result = null;

        System.out.println(String.format("Встретились рыбки %s и %s", male.getName(), female.getName()));

        Random random = new Random();

        if (random.nextInt(2) > 0) {
            result = Fish.giveBirthToFish(Gender.values()[random.nextInt(2)]);
        }

        return result;
    }

    /**
     * getNextNumber - получить следующий номер рыбки.
     * @return Long.
     */
    private static long getNextNumber() {
        LOCK.lock();
            long nextNumber = ++Fish.nextNumber;
        LOCK.unlock();

        return nextNumber;
    }

    /**
     * fishDied - прошел или нет жизненый цикл.
     * @return Long.
     */
    public boolean fishDied() {
        return this.lifetime <= new Date().getTime() - this.dateOfBirth;
    }

    /**
     * getName.
     * @return String.
     */
    public String getName() {
        return this.name;
    }

    /**
     * getGender.
     * @return Gender.
     */
    public Gender getGender() {
        return this.gender;
    }

    /**
     * getDateOfBirth.
     * @return long.
     */
    public long getDateOfBirth() {
        return this.dateOfBirth;
    }

    /**
     * getLifetime.
     * @return int.
     */
    public int getLifetime() {
        return this.lifetime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Fish fish = (Fish) o;

        if (dateOfBirth != fish.dateOfBirth) {
            return false;
        }
        if (lifetime != fish.lifetime) {
            return false;
        }
        if (name != null ? !name.equals(fish.name) : fish.name != null) {
            return false;
        }

        return gender == fish.gender;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (int) (dateOfBirth ^ (dateOfBirth >>> 32));
        result = 31 * result + lifetime;
        return result;
    }

    @Override
    public String toString() {
        return "Fish{" + "name='" + name + '\'' + ", gender=" + gender
                + ", dateOfBirth=" + dateOfBirth + ", lifetime=" + lifetime + '}';
    }

    @Override
    public int compareTo(Object o) {
        Long timeLeftO1 = this.getLifetime() - (new Date().getTime() - this.getDateOfBirth());
        Long timeLeftO2 = ((Fish) o).getLifetime() - (new Date().getTime() - ((Fish) o).getDateOfBirth());

        return timeLeftO1.compareTo(timeLeftO2);
    }
}