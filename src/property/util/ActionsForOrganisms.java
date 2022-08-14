package property.util;

import abstractions.Animal;
import controller.CellInitializer;
import controller.Coordinate;

import java.util.concurrent.ThreadLocalRandom;

public class ActionsForOrganisms {
    private CellInitializer cellInitializer = new CellInitializer();

    public void movePredators() {
        for (int i = 0; i < cellInitializer.island.getCellCoordinateXLength(); i++) {
            for (int j = 0; j < cellInitializer.island.getCellCoordinateYLength(); j++) {
                if (!(cellInitializer.island.getCELLS()[i][j].getPredatorList().isEmpty())) {
                    Animal animal = cellInitializer.island.getCELLS()[i][j].getPredatorList().
                            get((cellInitializer.island.getCELLS()[i][j].getPredatorList().size()) == 1 ? 0 : ThreadLocalRandom.
                                    current().nextInt(cellInitializer.island.getCELLS()[i][j].getPredatorList().size() - 1));
                    animal.move(new Coordinate(i, j), animal);
                }
            }
        }
    }

    public void moveHerbivores() {
        for (int i = 0; i < cellInitializer.island.getCellCoordinateXLength(); i++) {
            for (int j = 0; j < cellInitializer.island.getCellCoordinateYLength(); j++) {
                if (!(cellInitializer.island.getCELLS()[i][j].getHerbivoreList().isEmpty())) {
                    Animal animal = cellInitializer.island.getCELLS()[i][j].getHerbivoreList().
                            get((cellInitializer.island.getCELLS()[i][j].getHerbivoreList().size()) == 1 ? 0 : ThreadLocalRandom.
                                    current().nextInt(cellInitializer.island.getCELLS()[i][j].getHerbivoreList().size() - 1));
                    animal.move(new Coordinate(i, j), animal);
                }
            }
        }
    }

    public void feedHerbivores() {
        for (int i = 0; i < cellInitializer.island.getCellCoordinateXLength(); i++) {
            for (int j = 0; j < cellInitializer.island.getCellCoordinateYLength(); j++) {
                if (!(cellInitializer.island.getCELLS()[i][j].getHerbivoreList().isEmpty())) {
                    Animal animal = cellInitializer.island.getCELLS()[i][j].getHerbivoreList().
                            get((cellInitializer.island.getCELLS()[i][j].getHerbivoreList().size()) == 1 ? 0 : ThreadLocalRandom.
                                    current().nextInt(cellInitializer.island.getCELLS()[i][j].getHerbivoreList().size() - 1));
                    animal.eat(new Coordinate(i, j), animal);
                }
            }
        }
    }

    public void feedPredators(){
        for (int i = 0; i < cellInitializer.island.getCellCoordinateXLength(); i++) {
            for (int j = 0; j < cellInitializer.island.getCellCoordinateYLength(); j++) {
                if (!(cellInitializer.island.getCELLS()[i][j].getPredatorList().isEmpty())) {
                    Animal animal = cellInitializer.island.getCELLS()[i][j].getPredatorList().
                            get((cellInitializer.island.getCELLS()[i][j].getPredatorList().size()) == 1 ? 0 : ThreadLocalRandom.
                                    current().nextInt(cellInitializer.island.getCELLS()[i][j].getPredatorList().size() - 1));
                    animal.eat(new Coordinate(i, j), animal);
                }
            }
        }
    }

    public void bornPredators(){
        for (int i = 0; i < cellInitializer.island.getCellCoordinateXLength(); i++) {
            for (int j = 0; j < cellInitializer.island.getCellCoordinateYLength(); j++) {
                if (!(cellInitializer.island.getCELLS()[i][j].getPredatorList().isEmpty())) {
                    Animal animal = cellInitializer.island.getCELLS()[i][j].getPredatorList().
                            get((cellInitializer.island.getCELLS()[i][j].getPredatorList().size()) == 1 ? 0 : ThreadLocalRandom.
                                    current().nextInt(cellInitializer.island.getCELLS()[i][j].getPredatorList().size() - 1));
                    animal.bornOrganism(new Coordinate(i, j));
                }
            }
        }
    }

    public void bornHerbivores(){
        for (int i = 0; i < cellInitializer.island.getCellCoordinateXLength(); i++) {
            for (int j = 0; j < cellInitializer.island.getCellCoordinateYLength(); j++) {
                if (!(cellInitializer.island.getCELLS()[i][j].getHerbivoreList().isEmpty())) {
                    Animal animal = cellInitializer.island.getCELLS()[i][j].getHerbivoreList().
                            get((cellInitializer.island.getCELLS()[i][j].getHerbivoreList().size()) == 1 ? 0 : ThreadLocalRandom.
                                    current().nextInt(cellInitializer.island.getCELLS()[i][j].getHerbivoreList().size() - 1));
                    animal.bornOrganism(new Coordinate(i, j));
                }
            }
        }
    }
}
