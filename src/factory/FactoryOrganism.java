package factory;

import abstractions.Animal;
import entity.organism.herbivores.*;
import entity.organism.plants.Plant;
import entity.organism.predators.*;
import property.organismproperty.herbivoreproperty.*;
import property.organismproperty.plantproperty.PlantProperties;
import property.organismproperty.predatorproperty.*;

public class FactoryOrganism {
    private static FactoryOrganism factoryOrganism;

    private FactoryOrganism() {
    }

    public Animal getRandomAnimal(int animalNum){
        return switch (animalNum) {
            case 0 -> new Buffalo(BuffaloProperties.MIN_WEIGHT_BUFFALO);
            case 1 -> new Caterpillar(CaterpillarProperties.MIN_WEIGHT_CATERPILLAR);
            case 2 -> new Deer(DeerProperties.MIN_WEIGHT_DEER);
            case 3 -> new Duck(DuckProperties.MIN_WEIGHT_DUCK);
            case 4 -> new Goat(GoatProperties.MIN_WEIGHT_GOAT);
            case 5 -> new Horse(HorseProperties.MIN_WEIGHT_HORSE);
            case 6 -> new Mouse(MouseProperties.MIN_WEIGHT_MOUSE);
            case 7 -> new Rabbit(RabbitProperties.MIN_WEIGHT_RABBIT);
            case 8 -> new Sheep(SheepProperties.MIN_WEIGHT_SHEEP);
            case 9 -> new Bear(BearProperties.MIN_WEIGHT_BEAR);
            case 10 -> new Boa(BoaProperties.MIN_WEIGHT_BOA);
            case 11 -> new Eagle(EagleProperties.MIN_WEIGHT_EAGLE);
            case 12 -> new Fox(FoxProperties.MIN_WEIGHT_FOX);
            case 13 -> new Wolf(WolfProperties.MIN_WEIGHT_WOLF);
            default -> new Boar(BoarProperties.MIN_WEIGHT_BOAR);
        };
    }
    public synchronized static FactoryOrganism getFactoryInstance() {
        if (factoryOrganism == null) {
            factoryOrganism = new FactoryOrganism();
        }
        return factoryOrganism;
    }

    public Plant getPlantFactory() {
        return new Plant(PlantProperties.MIN_WEIGHT_PLANT);
    }
}
