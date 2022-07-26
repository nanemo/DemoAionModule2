package services;

public abstract class Predators<T> extends Animal {

    public Predators(int coordinateX, int coordinateY, String NAME, Integer SPEED, Double SATIATE, Double weight) {
        super(coordinateX, coordinateY, NAME, SPEED, SATIATE, weight);
    }
}
