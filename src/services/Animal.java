package services;

public abstract class Animal extends Creature {

    public static final int STRAIGHT = 1;
    public static final int TO_LEFT = 2;
    public static final int TO_RIGHT = 3;
    public static final int BACK = 4;

    public Animal(int coordinateX, int coordinateY, String NAME, Integer SPEED, Double SATIATE, Double weight) {
        super(coordinateX, coordinateY, NAME, SPEED, SATIATE, weight);
    }

    public abstract void moveDirection(Animal animal, int x);

    public abstract void bread();

    public abstract void die();

}
