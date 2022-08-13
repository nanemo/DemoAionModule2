package entity.organism.plants;

import controller.CellInitializer;
import controller.Coordinate;
import lombok.Getter;
import lombok.Setter;
import property.organismproperty.plantproperty.PlantProperties;
import property.util.BornOrganism;

import java.util.concurrent.ThreadLocalRandom;

@Getter
@Setter
public class Plant implements BornOrganism {
    private Double weight;

    public Plant(Double weight) {
        this.weight = weight;
    }

    private CellInitializer cellInitializer = new CellInitializer();

    @Override
    public void bornOrganism(Coordinate coordinate) {
        if (ThreadLocalRandom.current().nextBoolean() && plantCountIsNotFull(coordinate)) {
            Plant newGrowPlant = new Plant(PlantProperties.MIN_WEIGHT_PLANT);
            cellInitializer.initializeNewGrowPlantToCell(coordinate, newGrowPlant);
        }
    }

    private boolean plantCountIsNotFull(Coordinate coordinateForCount) {
        return cellInitializer.island.getCells(coordinateForCount).getPlantList().size() < PlantProperties.MAX_COUNT_PLANT;
    }

}
