package abstractions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Animal {
     private final String NAME;
     private final Integer SPEED;
     private final Double SATIATE;
     private Double weight;

    public static final int STRAIGHT = 1;
    public static final int TO_LEFT = 2;
    public static final int TO_RIGHT = 3;
    public static final int BACK = 4;

    public Animal(String NAME, Integer SPEED, Double SATIATE, Double weight) {
        this.NAME = NAME;
        this.SPEED = SPEED;
        this.SATIATE = SATIATE;
        this.weight = weight;
    }

    public abstract void eat();

    public abstract void bread();

    public abstract void die();

}
