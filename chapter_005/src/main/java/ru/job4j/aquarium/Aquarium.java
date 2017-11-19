package ru.job4j.aquarium;

import java.util.Date;
import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Aquarium.
 *
 * @author Stanislav (376825@mail.ru)
 * @since 18.11.2017
 */
public class Aquarium implements Runnable {
    /**
     * изначальное количество рыб.
     */
    private final int initialNumberFish;
    /**
     */
    private final ConcurrentSkipListSet<Fish> maleFishContainer = new ConcurrentSkipListSet<>();
    /**
     */
    private final ConcurrentSkipListSet<Fish> femaleFishContainer = new ConcurrentSkipListSet<>();

    /**
     * Constructor.
     * @param initialNumberFish - изначальное количество рыб.
     */
    public Aquarium(int initialNumberFish) {
        this.initialNumberFish = initialNumberFish;
    }

    @Override
    public void run() {
        initialization();
        life();
    }

    /**
     * initialization - заселение рыб в аквариум.
     */
    private void initialization() {
        int numberFishFiftyFifty = this.initialNumberFish / 2;

        for (int i = 1; i <= numberFishFiftyFifty; i++) {
            this.maleFishContainer.add(Fish.giveBirthToFish(Gender.MALE));
        }

        numberFishFiftyFifty = this.initialNumberFish - numberFishFiftyFifty;
        for (int i = 1; i <= numberFishFiftyFifty; i++) {
            this.femaleFishContainer.add(Fish.giveBirthToFish(Gender.FEMALE));
        }
    }

    /**
     * life - пока в аквариуме есть жизнь.
     */
    private void life() {
        ExecutorService service = Executors.newCachedThreadPool();

        Thread lifeCycleOfDeathMale = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                cleanAquariumFromDeadFish(maleFishContainer);
            }
        });

        Thread lifeCycleOfDeathFemale = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                cleanAquariumFromDeadFish(femaleFishContainer);
            }
        });

        service.submit(lifeCycleOfDeathMale);
        service.submit(lifeCycleOfDeathFemale);

        while (!Thread.currentThread().isInterrupted()
                && !this.maleFishContainer.isEmpty() || !this.femaleFishContainer.isEmpty()) {

            try {
                Thread.sleep(new Random().nextInt(1000));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            crossing();

            System.out.println(String.format("Популяция аквариума на момент %s составляет %s особей",
                    new Date(), this.maleFishContainer.size() + this.femaleFishContainer.size()));
        }

        service.shutdownNow();
        System.out.println("Все рыбы вымерли. Симуляция окончена.");
    }

    /**
     * addFish.
     */
    public void addFish(Fish fish) {
        ConcurrentSkipListSet<Fish> container = fish.getGender() == Gender.MALE
                ? this.maleFishContainer : this.femaleFishContainer;

        container.add(fish);
    }

    /**
     * cleanAquariumFromDeadFish - если рыбки умерли, убираем с аквариума.
     */
    private void cleanAquariumFromDeadFish(ConcurrentSkipListSet<Fish> containerFish) {
        Iterator<Fish> iterator = containerFish.iterator();

        while (iterator.hasNext() && !Thread.currentThread().isInterrupted()) {
            Fish currentFish = iterator.next();
            if (currentFish.fishDied()) {
                iterator.remove();
                System.out.println(String.format("Умерла рыбка %s", currentFish.getName()));
            }
        }
    }

    /**
     * crossing - скрещивание.
     */
    private void crossing() {
        Fish maleFish = getFishByStrategy(this.maleFishContainer);
        Fish femaleFish = getFishByStrategy(this.femaleFishContainer);

        if (maleFish != null && femaleFish != null) {
            Fish newFish = Fish.conceiveFish(maleFish, femaleFish);

            if (newFish != null) {
                addFish(newFish);
            }
        }
    }

    /**
     * getFishByStrategy - получить рыбку для скрещивания по какой то логике.
     * В данный момент с конца (у молодняка + кому больше осталось жить больше шансов).
     */
    private Fish getFishByStrategy(ConcurrentSkipListSet<Fish> containerFish) {
        return containerFish.isEmpty() ? null : containerFish.first();
    }
}