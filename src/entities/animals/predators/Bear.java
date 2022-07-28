package entities.animals.predators;

import services.Animal;
import services.Predators;

public class Bear extends Predators {

    private static Animal animal;

    public Bear(int coordinateX, int coordinateY, String NAME, Integer SPEED, Double SATIATE, Double weight) {
        super(coordinateX, coordinateY, NAME, SPEED, SATIATE, weight);
    }

    @Override
    public void moveDirection(Animal animal, int x) {

    }

    public void eat() {

    }

    public void move() {

    }

    public void moveDirection() {

    }

    @Override
    public void bread() {

    }

    @Override
    public void die() {

    }
}
