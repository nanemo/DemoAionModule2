package entities.animals.predators;

import services.Animal;
import services.Predators;

public class Wolf extends Predators {

    private int coordinateX;
    private int coordinateY;
    private static Animal animal;

    private Wolf(String NAME, Integer SPEED, Double SATIATE, Double weight) {
        super(NAME, SPEED, SATIATE, weight);
    }

    public static Animal getInstance() {
        if (animal == null) {
            animal = new Wolf("Wolf", 3, 8.00, 50.00);
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
