package main;

import controller.CellInitializer;
import property.util.ActionsForOrganisms;
import property.util.StaticsOrganism;

import java.util.concurrent.Executors;

public class Start {
    private static CellInitializer cellInitializer = new CellInitializer();
    private static ActionsForOrganisms actionsForOrganisms = new ActionsForOrganisms();

    public static void main(String[] args) {
        Executors.newScheduledThreadPool(3).scheduleAtFixedRate(, 15,)
        cellInitializer.primaryInitializerOrganismToCell();
        // isExist ile yoxla animal var ya yox

        Runnable aaa = new Runnable() {
            @Override
            public void run() {
                actionsForOrganisms.moveHerbivores();
                actionsForOrganisms.movePredators();
                actionsForOrganisms.feedHerbivores();
                actionsForOrganisms.feedPredators();
                actionsForOrganisms.bornHerbivores();
                actionsForOrganisms.bornPredators();
                StaticsOrganism.staticsForCountAnimalsAndPlants();
                StaticsOrganism.staticsAboutWeightAnimals();
                System.out.println("=======================================================================");
            }
        };
    }
}
