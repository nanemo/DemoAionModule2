package main;

import controller.CellInitializer;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * This class creates tasks for Threads
 */
public class ScheduledThreadTaskManager {

    private CellInitializer cellInitializer = new CellInitializer();
    static Thread thread = Thread.currentThread();

    public enum Mode {
        INDEFINITE, FIXED_NO_OF_TIMES
    }

    /**
     * Method chooses primary task for starting and calls every method for ScheduledExecutorService.
     */
    public ScheduledThreadTaskManager(Mode mode) {
        if (mode == Mode.INDEFINITE) {
            ScheduledExecutorService singleThreadScheduledExecutor = Executors.newSingleThreadScheduledExecutor();
            singleThreadScheduledExecutor.schedule(() -> {
                try {
                    cellInitializer.primaryInitializerOrganismToCell();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }, 0, TimeUnit.SECONDS);
        } else if (mode == Mode.FIXED_NO_OF_TIMES) {
            try {
//                FixedOrganismBornExecutionRunnable b = new FixedOrganismBornExecutionRunnable();
//                FixedOrganismFeedExecutionRunnable c = new FixedOrganismFeedExecutionRunnable();
//                FixedOrganismMoveExecutionRunnable d = new FixedOrganismMoveExecutionRunnable();
//                FixedAnimalStatisticsExecutionRunnable a = new FixedAnimalStatisticsExecutionRunnable();
//
//                Thread thread2 = new Thread(b);
//                Thread thread3 = new Thread(c);
//                Thread thread4 = new Thread(d);
//                Thread thread1 = new Thread(a);
//                thread2.start();
//                thread3.start();
//                thread4.start();
//                thread1.start();


                ScheduledExecutorService multiThreadExecutorService = Executors.newScheduledThreadPool(3);
                System.out.println(Thread.currentThread().getName() + " -------- " + Thread.currentThread().getPriority());
                multiThreadExecutorService.scheduleAtFixedRate(
                        new FixedOrganismBornExecutionRunnable(), 1, 3, TimeUnit.SECONDS);
                multiThreadExecutorService.scheduleAtFixedRate(
                        new FixedOrganismMoveExecutionRunnable(), 2, 3, TimeUnit.SECONDS);
                multiThreadExecutorService.scheduleAtFixedRate(
                        new FixedOrganismFeedExecutionRunnable(), 3, 3, TimeUnit.SECONDS);
                multiThreadExecutorService.scheduleAtFixedRate(
                        new FixedAnimalStatisticsExecutionRunnable(), 4, 3, TimeUnit.SECONDS);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

}