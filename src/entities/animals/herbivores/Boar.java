package entities.animals.herbivores;

import services.Animal;
import services.Herbivores;

public class Boar extends Herbivores {
    private int coordinateX;
    private int coordinateY;
    private static Animal animal;

    private Boar(String NAME, Integer SPEED, Double SATIATE, Double weight) {
        super(NAME, SPEED, SATIATE, weight);
    }

    public static Animal getInstance() {
        if (animal == null) {
            animal = new Boar("Boar", 2, 50.00, 400.00);
            return animal;
        } else {
            return animal;
        }
    }

    public void setCoordinates() {
//        coordinateX = animalsCoordinate.setPosition();
    }

    public int getCoordinateX() {
        return coordinateX;
    }

    public int getCoordinateY() {
        return coordinateY;
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
