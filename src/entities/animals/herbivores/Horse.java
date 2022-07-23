package entities.animals.herbivores;

import services.Animal;
import services.Herbivores;

public class Horse extends Herbivores {

    private int coordinateX;
    private int coordinateY;
    private static Animal animal;

    private Horse(String NAME, Integer SPEED, Double SATIATE, Double weight) {
        super(NAME, SPEED, SATIATE, weight);
    }

    public static Animal getInstance() {
        if (animal == null) {
            animal = new Horse("Horse", 4, 60.00, 400.00);
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

    @Override
    public String toString() {
        return super.toString();
    }

}
