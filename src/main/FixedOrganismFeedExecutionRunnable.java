package main;

import property.util.ActionsForOrganisms;

public class FixedOrganismFeedExecutionRunnable implements Runnable {
    private ActionsForOrganisms actionsForOrganisms = new ActionsForOrganisms();

    @Override
    public void run() {
        try {
            actionsForOrganisms.feedHerbivores();
            actionsForOrganisms.feedPredators();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
