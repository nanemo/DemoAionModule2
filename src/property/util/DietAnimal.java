package property.util;

import abstractions.Animal;
import abstractions.Herbivore;
import abstractions.Predator;
import controller.CellInitializer;
import controller.Coordinate;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Interface has a default method for every animal
 */
public interface DietAnimal {
    CellInitializer cellInitializer = new CellInitializer();

    /**
     * Default method for loss weight animals. All animals use this method
     */
    default <T extends Animal> double weightLoss(T t) {
        if (ThreadLocalRandom.current().nextBoolean()) {
            t.setWeight(t.getWeight() - 1);
            return t.getWeight();
        }
        return t.getWeight();
    }

    /** This method is same in other animal classes.
     * We can take it to interface and do that method default for all implement classes.
     * But for now we configured the island_model with threads in a pool. I don't want to take this method because
     * might be we will lose control on ThreadTaskManager. But further i will take a look on this application and
     * finish it.*/
    default <T extends Animal> void dietAnimal(Coordinate coordinate, T t) {
        if (t instanceof Herbivore && weightLoss(t) <= 0) {
            cellInitializer.getCellByCoordinates(coordinate).getHerbivoreList().remove(t);
        } else if (t instanceof Predator && weightLoss(t) <= 0){
            cellInitializer.getCellByCoordinates(coordinate).getPredatorList().remove(t);
        }
    }
}
