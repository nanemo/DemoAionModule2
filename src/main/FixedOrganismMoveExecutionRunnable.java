package main;

import property.util.ActionsForOrganisms;

import java.time.LocalTime;

/**
 * This class runnable and get the move action for animals
 */
public class FixedOrganismMoveExecutionRunnable implements Runnable {
    private ActionsForOrganisms actionsForOrganisms = new ActionsForOrganisms();

    @Override
    public void run() {
        try {
            Thread.currentThread().setPriority(10);
            System.out.println(Thread.currentThread().getName() + " -------- " + Thread.currentThread().getPriority());
            System.out.println("Thread D  - " + LocalTime.now());
            actionsForOrganisms.moveHerbivores();
            actionsForOrganisms.movePredators();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
