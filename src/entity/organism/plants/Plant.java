package entity.organism.plants;

import controller.CellInitializer;
import controller.Coordinate;
import lombok.Getter;
import lombok.Setter;
import property.organismproperty.plantproperty.PlantProperties;
import property.util.BornOrganism;

@Getter
@Setter
public class Plant implements BornOrganism {
    private double weight;

    public Plant(Double weight) {
        this.weight = weight;
    }

    private CellInitializer cellInitializer = new CellInitializer();

    @Override
    public void bornOrganism(Coordinate coordinate) {
        if (plantCountIsNotFull(coordinate)) {
            cellInitializer.initializeNewGrowPlantToCell(coordinate, this);
        }
    }

    private boolean plantCountIsNotFull(Coordinate coordinateForCount) {
        return cellInitializer.island.getCells(coordinateForCount).getPlantList().size() < PlantProperties.MAX_COUNT_PLANT;
    }

}
