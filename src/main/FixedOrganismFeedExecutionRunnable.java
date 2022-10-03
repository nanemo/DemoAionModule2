package main;

import property.util.ActionsForOrganisms;

public class FixedOrganismFeedExecutionRunnable implements Runnable {
    private ActionsForOrganisms actionsForOrganisms = new ActionsForOrganisms();

    @Override
    public void run() {
        try {
            Thread.currentThread().setPriority(10);
            System.out.println(Thread.currentThread().getName() + " -------- " + Thread.currentThread().getPriority());
            actionsForOrganisms.feedHerbivores();
            actionsForOrganisms.feedPredators();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
