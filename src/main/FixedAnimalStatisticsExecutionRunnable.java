package main;

import property.util.StaticsOrganism;

import java.time.LocalTime;

/**
 * This class is Runnable and get the statics about animals and plants.
 */
public class FixedAnimalStatisticsExecutionRunnable implements Runnable {

    @Override
    public void run() {
        try {
            Thread.currentThread().setPriority(10);
            System.out.println(Thread.currentThread().getName() + " -------- " + Thread.currentThread().getPriority());
            System.out.println("Thread B - "+ LocalTime.now());
            StaticsOrganism.staticsForCountAnimalsAndPlants();
            StaticsOrganism.staticsAboutWeightAnimals();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
