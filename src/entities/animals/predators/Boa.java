package entities.animals.predators;

import services.Animal;
import services.Predators;

public class Boa extends Predators {
    private int coordinateX;
    private int coordinateY;
    private static Animal animal;

    private Boa(String NAME, Integer SPEED, Double SATIATE, Double weight) {
        super(NAME, SPEED, SATIATE, weight);
    }

    public static Animal getInstance() {
        if (animal == null) {
            animal = new Boa("Boa", 1, 3.00, 15.00);
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
