package main;

import controller.CellInitializer;
import property.util.ActionsForOrganisms;
import property.util.StaticsOrganism;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/** This class creates tasks for Threads*/
public class ScheduledThreadTaskManager {
    private static ActionsForOrganisms actionsForOrganisms = new ActionsForOrganisms();
    private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private CellInitializer cellInitializer = new CellInitializer();

    public enum Mode {
        INDEFINITE, FIXED_NO_OF_TIMES
    }

    /** Method chooses primary task for starting and calls every methods for ScheduledExecutorService.*/
    public ScheduledThreadTaskManager(Mode mode) {
        Runnable taskForInitializeIsland = () -> cellInitializer.primaryInitializerOrganismToCell();
        Runnable taskForStatisticsOrganism = () -> {
            StaticsOrganism.staticsForCountAnimalsAndPlants();
            StaticsOrganism.staticsAboutWeightAnimals();
        };
        Runnable taskForActionAnimals1 = () -> {
            actionsForOrganisms.moveHerbivores();
            actionsForOrganisms.movePredators();
            actionsForOrganisms.feedHerbivores();
            actionsForOrganisms.feedPredators();
            actionsForOrganisms.bornHerbivores();
            actionsForOrganisms.bornPredators();
        };
        Runnable taskForActionAnimals2 = () -> {
            actionsForOrganisms.moveHerbivores();
            actionsForOrganisms.movePredators();
            actionsForOrganisms.feedHerbivores();
            actionsForOrganisms.feedPredators();
            actionsForOrganisms.bornHerbivores();
            actionsForOrganisms.bornPredators();
        };

        if (mode == Mode.INDEFINITE) {
            runNTimesIslandInitializer(taskForInitializeIsland, 1, 5, TimeUnit.SECONDS, scheduler);
        } else if (mode == Mode.FIXED_NO_OF_TIMES) {
            runNTimesStatisticsOrganism(taskForStatisticsOrganism, 5, 5, TimeUnit.SECONDS, scheduler);
            runNTimesOrganismAction(taskForActionAnimals1, 5, 5, TimeUnit.SECONDS, scheduler);
            runNTimesOrganismAction(taskForActionAnimals2, 5, 5, TimeUnit.SECONDS, scheduler);
        }
    }

    private void runNTimesOrganismAction(Runnable task, int maxRunCount, long period, TimeUnit unit, ScheduledExecutorService executor) {
        new FixedPrimaryIslandInitializerExecutionRunnable(task, maxRunCount).runNTimes(executor, period, unit);
    }

    private void runNTimesStatisticsOrganism(Runnable task, int maxRunCount, long period, TimeUnit unit, ScheduledExecutorService executor) {
        new FixedOrganismActionExecutionRunnable(task, maxRunCount).runNTimes(executor, period, unit);
    }

    public void runNTimesIslandInitializer(Runnable task, int maxRunCount, long period, TimeUnit unit, ScheduledExecutorService executor) {
        new FixedAnimalStatisticsExecutionRunnable(task, maxRunCount).runNTimes(executor, period, unit);
    }



}
