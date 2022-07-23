package entities.animals.herbivores;

import services.Animal;
import services.Herbivores;

public class Sheep extends Herbivores {
    private int coordinateX;
    private int coordinateY;
    private static Animal animal;

    private Sheep(String NAME, Integer SPEED, Double SATIATE, Double weight) {
        super(NAME, SPEED, SATIATE, weight);
    }

    public static Animal getInstance() {
        if (animal == null) {
            animal = new Sheep("Sheep", 3, 15.00, 70.00);
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
