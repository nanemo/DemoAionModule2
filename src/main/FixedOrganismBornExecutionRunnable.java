package main;

import property.util.ActionsForOrganisms;

public class FixedOrganismBornExecutionRunnable implements Runnable{
    private ActionsForOrganisms actionsForOrganisms = new ActionsForOrganisms();

    @Override
    public void run() {
        try {
            actionsForOrganisms.bornHerbivores();
            actionsForOrganisms.bornPredators();
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
