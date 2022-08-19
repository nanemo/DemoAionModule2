package main;

import controller.CellInitializer;
import property.util.ActionsForOrganisms;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * This class creates tasks for Threads
 */
public class ScheduledThreadTaskManager {
    private static ActionsForOrganisms actionsForOrganisms = new ActionsForOrganisms();
    private ScheduledExecutorService multiThreadExecutorService = Executors.newScheduledThreadPool(3);
    private ScheduledExecutorService singleThreadScheduledExecutor = Executors.newSingleThreadScheduledExecutor();
    private CellInitializer cellInitializer = new CellInitializer();
    private ReentrantLock reentrantLock = new ReentrantLock();

    public enum Mode {
        INDEFINITE, FIXED_NO_OF_TIMES
    }

    /**
     * Method chooses primary task for starting and calls every methods for ScheduledExecutorService.
     */
    public ScheduledThreadTaskManager(Mode mode) {
        if (mode == Mode.INDEFINITE) {
            singleThreadScheduledExecutor.schedule(new Runnable() {
                @Override
                public void run() {
                    try {
                        cellInitializer.primaryInitializerOrganismToCell();
                    }catch (Exception ex){
                        ex.printStackTrace();
                    }
                }
            }, 0, TimeUnit.SECONDS);
        } else if (mode == Mode.FIXED_NO_OF_TIMES) {
            try {
                multiThreadExecutorService.scheduleAtFixedRate(new FixedAnimalStatisticsExecutionRunnable(), 5, 5, TimeUnit.SECONDS);
                multiThreadExecutorService.scheduleAtFixedRate(new FixedOrganismMoveExecutionRunnable(), 6, 5, TimeUnit.SECONDS);
                multiThreadExecutorService.scheduleAtFixedRate(new FixedOrganismFeedExecutionRunnable(), 7, 5, TimeUnit.SECONDS);
                multiThreadExecutorService.scheduleAtFixedRate(new FixedOrganismBornExecutionRunnable(), 8, 5, TimeUnit.SECONDS);
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }


//        if (mode == Mode.INDEFINITE) {
//            runNTimesIslandInitializer(taskForInitializeIsland, 1, 5, TimeUnit.SECONDS, scheduler);
//        } else if (mode == Mode.FIXED_NO_OF_TIMES) {
////            runNTimesStatisticsOrganism(taskForStatisticsOrganism, 5, 5, TimeUnit.SECONDS, scheduler);
//            runNTimesOrganismAction(taskForActionAnimals, 5, 5, TimeUnit.SECONDS, scheduler);
//        }
//    }
//
//    private void runNTimesOrganismAction(Runnable task, int maxRunCount, long period, TimeUnit unit, ScheduledExecutorService executor) {
//        new FixedOrganismActionExecutionRunnable(task, maxRunCount, reentrantLock).runNTimes(executor, period, unit);
//    }
//
////    private void runNTimesStatisticsOrganism(Runnable task, int maxRunCount, long period, TimeUnit unit, ScheduledExecutorService executor) {
////        new FixedAnimalStatisticsExecutionRunnable(task, maxRunCount, reentrantLock).runNTimes(executor, period, unit);
////    }
//
//    public void runNTimesIslandInitializer(Runnable task, int maxRunCount, long period, TimeUnit unit, ScheduledExecutorService executor) {
//        new FixedPrimaryIslandInitializerExecutionRunnable(task, maxRunCount, reentrantLock).runNTimes(executor, period, unit);
//    }

    }
}