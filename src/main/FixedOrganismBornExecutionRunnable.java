package main;

import property.util.ActionsForOrganisms;

/** This class is runnable and runs the born processes*/
public class FixedOrganismBornExecutionRunnable implements Runnable {
    private ActionsForOrganisms actionsForOrganisms = new ActionsForOrganisms();

    @Override
    public void run() {
        try {
            actionsForOrganisms.bornHerbivores();
            actionsForOrganisms.bornPredators();
            actionsForOrganisms.growPlants();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
