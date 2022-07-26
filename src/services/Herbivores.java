package services;

public abstract class Herbivores extends Animal {

    public Herbivores(int coordinateX, int coordinateY, String NAME, Integer SPEED, Double SATIATE, Double weight) {
        super(coordinateX, coordinateY, NAME, SPEED, SATIATE, weight);
    }
}
