package main;

import property.util.ActionsForOrganisms;

public class FixedOrganismMoveExecutionRunnable implements Runnable {
    private ActionsForOrganisms actionsForOrganisms = new ActionsForOrganisms();
    @Override
    public void run() {
        try {
            actionsForOrganisms.moveHerbivores();
            actionsForOrganisms.movePredators();
         } catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
