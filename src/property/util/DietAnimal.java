package property.util;

import abstractions.Animal;

import java.util.concurrent.ThreadLocalRandom;
    /** Interface has a default method for every animals*/
public interface DietAnimal {
    /** Default method for loss weight animals. All animals use this method*/
    default <T extends Animal> double weightLoss(T t) {
        if (ThreadLocalRandom.current().nextBoolean()) {
            t.setWeight(t.getWeight() - 1);
            return t.getWeight();
        }
        return t.getWeight();
    }
}
