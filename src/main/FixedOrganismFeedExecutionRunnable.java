package main;

import property.util.ActionsForOrganisms;

import java.time.LocalTime;

public class FixedOrganismFeedExecutionRunnable implements Runnable {
    private ActionsForOrganisms actionsForOrganisms = new ActionsForOrganisms();

    @Override
    public void run() {
        try {
            Thread.currentThread().setPriority(10);
            System.out.println(Thread.currentThread().getName() + " -------- " + Thread.currentThread().getPriority());
            System.out.println("Thread A - " + LocalTime.now());
            actionsForOrganisms.feedHerbivores();
            actionsForOrganisms.feedPredators();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
