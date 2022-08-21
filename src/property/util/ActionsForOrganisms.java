package property.util;

import abstractions.Animal;
import controller.CellInitializer;
import controller.Coordinate;

import java.util.concurrent.ThreadLocalRandom;

/**
 * This class have move, feed and born methods
 */
public class ActionsForOrganisms {
    private static CellInitializer cellInitializer = new CellInitializer();


    /**
     * In this method you can move Predators to new coordinates
     */
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

    /**
     * In this method you can move Herbivores to new coordinates
     */
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

    /**
     * In this method Herbivores eat anything from same coordinate
     */
    public synchronized void feedHerbivores() {
        for (int i = 0; i < cellInitializer.island.getCellCoordinateXLength(); i++) {
            for (int j = 0; j < cellInitializer.island.getCellCoordinateYLength(); j++) {
                if (!(cellInitializer.island.getCELLS()[i][j].getHerbivoreList().isEmpty())) {
                    Animal animal = cellInitializer.island.getCELLS()[i][j].getHerbivoreList().
                            get((cellInitializer.island.getCELLS()[i][j].getHerbivoreList().size()) == 1 ? 0 : ThreadLocalRandom.
                                    current().nextInt(cellInitializer.island.getCELLS()[i][j].getHerbivoreList().size() - 1));
                    animal.eat(new Coordinate(i, j));
                }
            }
        }
    }

    /**
     * In this method Predators eat anything from same coordinate
     */
    public synchronized void feedPredators() {
        for (int i = 0; i < cellInitializer.island.getCellCoordinateXLength(); i++) {
            for (int j = 0; j < cellInitializer.island.getCellCoordinateYLength(); j++) {
                if (!(cellInitializer.island.getCELLS()[i][j].getPredatorList().isEmpty())) {
                    Animal animal = cellInitializer.island.getCELLS()[i][j].getPredatorList().
                            get((cellInitializer.island.getCELLS()[i][j].getPredatorList().size()) == 1 ? 0 : ThreadLocalRandom.
                                    current().nextInt(cellInitializer.island.getCELLS()[i][j].getPredatorList().size() - 1));
                    animal.eat(new Coordinate(i, j));
                }
            }
        }
    }

    /**
     * In this method Predators borne in the same coordinate
     */
    public void bornPredators() {
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

    /**
     * In this method Herbivores borne in the same coordinate
     */
    public void bornHerbivores() {
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
