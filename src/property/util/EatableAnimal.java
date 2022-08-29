package property.util;

import abstractions.Animal;
import controller.Coordinate;
import property.organismproperty.herbivoreproperty.*;
import property.organismproperty.plantproperty.PlantProperties;
import property.organismproperty.predatorproperty.BoaProperties;
import property.organismproperty.predatorproperty.FoxProperties;

/** This interface has default methods for animals. His methods get coordinate and generics objects. */
public interface EatableAnimal {
    void eat(Coordinate coordinate);

    default <T extends Animal> void eatMouse(T t) {
        t.setWeight(t.getWeight() + MouseProperties.MIN_WEIGHT_MOUSE);
    }

    default <T extends Animal> void eatCaterpillar(T t) {
        t.setWeight(t.getWeight() + CaterpillarProperties.MIN_WEIGHT_CATERPILLAR);
    }

    default <T extends Animal> void eatBoa(T t) {
        t.setWeight(t.getWeight() + BoaProperties.MIN_WEIGHT_BOA);
    }

    default <T extends Animal> void eatFox(T t) {
        t.setWeight(t.getWeight() + FoxProperties.MIN_WEIGHT_FOX);
    }

    default <T extends Animal> void eatHorse(T t) {
        t.setWeight(t.getWeight() + HorseProperties.MIN_WEIGHT_HORSE);
    }

    default <T extends Animal> void eatDeer(T t) {
        t.setWeight(t.getWeight() + DeerProperties.MIN_WEIGHT_DEER);
    }

    default <T extends Animal> void eatRabbit(T t) {
        t.setWeight(t.getWeight() + RabbitProperties.MIN_WEIGHT_RABBIT);
    }

    default <T extends Animal> void eatGoat(T t) {
        t.setWeight(t.getWeight() + GoatProperties.MIN_WEIGHT_GOAT);
    }

    default <T extends Animal> void eatSheep(T t) {
        t.setWeight(t.getWeight() + SheepProperties.MIN_WEIGHT_SHEEP);
    }

    default <T extends Animal> void eatBoar(T t) {
        t.setWeight(t.getWeight() + BoarProperties.MIN_WEIGHT_BOAR);
    }

    default <T extends Animal> void eatBuffalo(T t) {
        t.setWeight(t.getWeight() + BuffaloProperties.MIN_WEIGHT_BUFFALO);
    }

    default <T extends Animal> void eatDuck(T t) {
        t.setWeight(t.getWeight() + DuckProperties.MIN_WEIGHT_DUCK);
    }

    default <T extends Animal> void eatPlant(T t) {
        t.setWeight(t.getWeight() + PlantProperties.MIN_WEIGHT_PLANT);
    }

}
