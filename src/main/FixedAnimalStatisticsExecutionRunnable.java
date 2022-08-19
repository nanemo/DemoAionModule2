package main;

import property.util.StaticsOrganism;

/**
 * This class is Runnable and has run method.
 */
public class FixedAnimalStatisticsExecutionRunnable implements Runnable {

    @Override
    public void run() {
        try {
            StaticsOrganism.staticsForCountAnimalsAndPlants();
            StaticsOrganism.staticsAboutWeightAnimals();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
