package factory;

import abstractions.Animal;
import entity.organism.herbivores.*;
import entity.organism.plants.Plant;
import entity.organism.predators.*;
import property.organismproperty.herbivoreproperty.*;
import property.organismproperty.plantproperty.PlantProperties;
import property.organismproperty.predatorproperty.*;

import java.util.ArrayList;
import java.util.List;

public class FactoryAnimal {
    private List<Animal> listAnimalFactory = new ArrayList<>();
    private List<Plant> listPlantFactory = new ArrayList<>();
    private static FactoryAnimal factoryAnimal;

    private FactoryAnimal() {
        listAnimalFactory.add(new Boar(BoarProperties.MIN_WEIGHT_BOAR));
        listAnimalFactory.add(new Buffalo(BuffaloProperties.MIN_WEIGHT_BUFFALO));
        listAnimalFactory.add(new Caterpillar(CaterpillarProperties.MIN_WEIGHT_CATERPILLAR));
        listAnimalFactory.add(new Deer(DeerProperties.MIN_WEIGHT_DEER));
        listAnimalFactory.add(new Duck(DuckProperties.MIN_WEIGHT_DUCK));
        listAnimalFactory.add(new Goat(GoatProperties.MIN_WEIGHT_GOAT));
        listAnimalFactory.add(new Horse(HorseProperties.MIN_WEIGHT_HORSE));
        listAnimalFactory.add(new Mouse(MouseProperties.MIN_WEIGHT_MOUSE));
        listAnimalFactory.add(new Rabbit(RabbitProperties.MIN_WEIGHT_RABBIT));
        listAnimalFactory.add(new Sheep(SheepProperties.MIN_WEIGHT_SHEEP));
        listAnimalFactory.add(new Bear(BearProperties.MIN_WEIGHT_BEAR));
        listAnimalFactory.add(new Boa(BoaProperties.MIN_WEIGHT_BOA));
        listAnimalFactory.add(new Eagle(EagleProperties.MIN_WEIGHT_EAGLE));
        listAnimalFactory.add(new Fox(FoxProperties.MIN_WEIGHT_FOX));
        listAnimalFactory.add(new Wolf(WolfProperties.MIN_WEIGHT_WOLF));
        listPlantFactory.add(new Plant(PlantProperties.MIN_WEIGHT_PLANT));
    }

    public Animal getRandomAnimal(int animalNum){
        return switch (animalNum) {
            case 2 -> new Buffalo(BuffaloProperties.MIN_WEIGHT_BUFFALO);
            case 3 -> new Caterpillar(BuffaloProperties.MIN_WEIGHT_BUFFALO);
            case 4 -> new Deer(BuffaloProperties.MIN_WEIGHT_BUFFALO);
            case 5 -> new Duck(BuffaloProperties.MIN_WEIGHT_BUFFALO);
            case 6 -> new Goat(BuffaloProperties.MIN_WEIGHT_BUFFALO);
            case 7 -> new Horse(BuffaloProperties.MIN_WEIGHT_BUFFALO);
            case 8 -> new Mouse(BuffaloProperties.MIN_WEIGHT_BUFFALO);
            case 9 -> new Rabbit(BuffaloProperties.MIN_WEIGHT_BUFFALO);
            case 10 -> new Sheep(BuffaloProperties.MIN_WEIGHT_BUFFALO);
            case 11 -> new Bear(BuffaloProperties.MIN_WEIGHT_BUFFALO);
            case 12 -> new Boa(BuffaloProperties.MIN_WEIGHT_BUFFALO);
            case 13 -> new Eagle(BuffaloProperties.MIN_WEIGHT_BUFFALO);
            case 14 -> new Fox(BuffaloProperties.MIN_WEIGHT_BUFFALO);
            case 15 -> new Wolf(BuffaloProperties.MIN_WEIGHT_BUFFALO);
            default -> new Boar(BoarProperties.MIN_WEIGHT_BOAR);
        };
    }
    public synchronized static FactoryAnimal getFactoryAnimalInstance() {
        if (factoryAnimal == null) {
            factoryAnimal = new FactoryAnimal();
        }
        return factoryAnimal;
    }

    public List<Animal> getListAnimalFactory() {
        return listAnimalFactory;
    }

    public List<Plant> getListPlantFactory() {
        return listPlantFactory;
    }
}
