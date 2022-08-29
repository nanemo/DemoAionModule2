package abstractions;

import property.util.BornOrganism;
import property.util.DietAnimal;
import property.util.EatableAnimal;
import property.util.MovableAnimal;

/** Abstract Animal class has field weight for animals and field of directions for animal directions*/
public abstract class Animal implements MovableAnimal, EatableAnimal, DietAnimal, BornOrganism {
    private double weight;
    public static final int STRAIGHT = 1;
    public static final int TO_LEFT = 2;
    public static final int TO_RIGHT = 3;
    public static final int BACK = 4;

    public Animal(double weight) {
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

}
