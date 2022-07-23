package entities.animals.herbivores;

import services.Animal;
import services.Herbivores;

public class Deer extends Herbivores {

    private int coordinateX;
    private int coordinateY;
    private static Animal animal;

    private Deer(String NAME, Integer SPEED, Double SATIATE, Double weight) {
        super(NAME, SPEED, SATIATE, weight);
    }

    public static Animal getInstance() {
        if (animal == null) {
            animal = new Deer("Deer", 4, 50.00, 300.00);
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
