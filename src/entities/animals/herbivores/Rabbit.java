package entities.animals.herbivores;

import services.Animal;
import services.Herbivores;

public class Rabbit extends Herbivores {

    private int coordinateX;
    private int coordinateY;
    private static Animal animal;

    private Rabbit(String NAME, Integer SPEED, Double SATIATE, Double weight) {
        super(NAME, SPEED, SATIATE, weight);
    }

    public static Animal getInstance() {
        if (animal == null) {
            animal = new Rabbit("Rabbit", 2, 0.45, 2.00);
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
