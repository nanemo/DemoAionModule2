package entities.animals.predators;

import services.Animal;
import services.Predators;

public class Eagle extends Predators {
    private int coordinateX;
    private int coordinateY;
    private static Animal animal;

    private Eagle(String NAME, Integer SPEED, Double SATIATE, Double weight) {
        super(NAME, SPEED, SATIATE, weight);
    }

    public static Animal getInstance() {
        if (animal == null) {
            animal = new Eagle("Eagle", 3, 1.00, 6.00);
            return animal;
        } else {
            return animal;
        }

    }

    @Override
    public void eat() {

    }

    @Override
    public void move() {

    }

    @Override
    public void bread() {

    }

    @Override
    public void die() {

    }
}
