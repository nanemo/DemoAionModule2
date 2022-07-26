package entities.animals.predators;

import services.Animal;
import services.Predators;

public class Bear extends Predators {

    private static Animal animal;

    public Bear(int coordinateX, int coordinateY, String NAME, Integer SPEED, Double SATIATE, Double weight) {
        super(coordinateX, coordinateY, NAME, SPEED, SATIATE, weight);
    }

    public void eat() {

    }

    @Override
    public void move() {

    }

    @Override
    public void moveDirection() {

    }

    @Override
    public void bread() {

    }

    @Override
    public void die() {

    }
}
