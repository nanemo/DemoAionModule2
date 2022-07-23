package entities.animals.herbivores;

import services.Animal;
import services.Herbivores;

public class Goat extends Herbivores {
    private int coordinateX;
    private int coordinateY;
    private static Animal animal;

    private Goat(String NAME, Integer SPEED, Double SATIATE, Double weight) {
        super(NAME, SPEED, SATIATE, weight);
    }

    public static Animal getInstance() {
        if (animal == null) {
            animal = new Goat("Goat", 3, 10.00, 60.00);
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
