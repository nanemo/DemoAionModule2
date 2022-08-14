package property.util;

import abstractions.Animal;

import java.util.concurrent.ThreadLocalRandom;

public interface DietAnimal {

    default <T extends Animal> double weightLoss(T t) {
        if (ThreadLocalRandom.current().nextBoolean()) {
            t.setWeight(t.getWeight() - 1);
            return t.getWeight();
        }
        return t.getWeight();
    }
}
