package services;

import controller.AnimalsCoordinate;

public abstract class Animal<T> {
    private final String NAME;
    private final Integer SPEED;
    private final Double SATIATE;
    private Double weight;
    public AnimalsCoordinate<T> animalsCoordinate;

    public Animal(String NAME, Integer SPEED, Double SATIATE, Double weight) {
        this.NAME = NAME;
        this.SPEED = SPEED;
        this.SATIATE = SATIATE;
        this.weight = weight;
    }

    public String getNAME() {
        return NAME;
    }

    public Integer getSPEED() {
        return SPEED;
    }

    public Double getSATIATE() {
        return SATIATE;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public abstract void eat();

    public abstract void move();

    public abstract void bread();

    public abstract void die();


}
