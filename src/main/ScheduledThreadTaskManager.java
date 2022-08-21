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
                multiThreadExecutorService.scheduleAtFixedRate(new FixedOrganismMoveExecutionRunnable(), 3, 3, TimeUnit.SECONDS);
                multiThreadExecutorService.scheduleAtFixedRate(new FixedOrganismFeedExecutionRunnable(), 4, 3, TimeUnit.SECONDS);
                multiThreadExecutorService.scheduleAtFixedRate(new FixedOrganismBornExecutionRunnable(), 5, 3, TimeUnit.SECONDS);
                multiThreadExecutorService.scheduleAtFixedRate(new FixedAnimalStatisticsExecutionRunnable(), 10, 10, TimeUnit.SECONDS);
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }

}