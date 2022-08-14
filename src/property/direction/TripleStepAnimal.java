package property.direction;

import controller.CellInitializer;
/** This class for triple step Animals. Here methods define new coordinates*/
public class TripleStepAnimal {
    private CellInitializer cellInitializer = new CellInitializer();

    public int defineNewCoordinateStraightDirectionForTripleStep(int coordinateX, int step) {
        if (coordinateX == 0) {
            return cellInitializer.island.getCellCoordinateXLength() - 1 - step;
        } else if (coordinateX == 1) {
            return cellInitializer.island.getCellCoordinateXLength() - step;
        } else if (coordinateX == 2) {
            return cellInitializer.island.getCellCoordinateXLength() - 1;
        } else {
            return coordinateX - step;
        }
    }

    public int defineNewCoordinateBackDirectionForTripleStep(int coordinateX, int step) {
        if (coordinateX == cellInitializer.island.getCellCoordinateXLength() - 1) {
            return step;
        } else if (coordinateX == cellInitializer.island.getCellCoordinateXLength() - 2) {
            return 2;
        } else if (coordinateX == cellInitializer.island.getCellCoordinateXLength() - 3) {
            return 1;
        } else {
            return coordinateX + step;
        }
    }

    public int defineNewCoordinateRightDirectionForTripleStep(int coordinateY, int step) {
        if (coordinateY == cellInitializer.island.getCellCoordinateYLength() - 1) {
            return step;
        } else if (coordinateY == cellInitializer.island.getCellCoordinateYLength() - 2) {
            return 2;
        } else if (coordinateY == cellInitializer.island.getCellCoordinateYLength() - 3) {
            return 1;
        } else {
            return coordinateY + step;
        }
    }

    public int defineNewCoordinateLeftDirectionForTripleStep(int coordinateY, int step) {
        if (coordinateY == 0) {
            return cellInitializer.island.getCellCoordinateYLength() - 1 - step;
        } else if (coordinateY == 1) {
            return cellInitializer.island.getCellCoordinateYLength() - step;
        } else if (coordinateY == 2) {
            return cellInitializer.island.getCellCoordinateYLength() - 1;
        } else {
            return coordinateY - step;
        }
    }
}
