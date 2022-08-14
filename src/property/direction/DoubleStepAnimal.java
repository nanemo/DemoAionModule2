package property.direction;

import controller.CellInitializer;
/** This class for double step Animals. Here methods define new coordinates*/
public class DoubleStepAnimal {
    private CellInitializer cellInitializer = new CellInitializer();

    public int defineNewCoordinateStraightDirectionForDoubleStep(int coordinateX, int step) {
        if (coordinateX == 0) {
            return cellInitializer.island.getCellCoordinateXLength() - 1 - step;
        } else if (coordinateX == 1) {
            return cellInitializer.island.getCellCoordinateXLength() - 1;
        } else {
            return coordinateX - step;
        }
    }

    public int defineNewCoordinateBackDirectionForDoubleStep(int coordinateX, int step) {
        if (coordinateX == cellInitializer.island.getCellCoordinateXLength() - 1) {
            return step;
        } else if (coordinateX == cellInitializer.island.getCellCoordinateXLength() - 2) {
            return 1;
        } else {
            return coordinateX + step;
        }
    }


    public int defineNewCoordinateRightDirectionForDoubleStep(int coordinateY, int step) {
        if (coordinateY == cellInitializer.island.getCellCoordinateYLength() - 1) {
            return step;
        } else if (coordinateY == cellInitializer.island.getCellCoordinateYLength() - 2) {
            return 1;
        } else {
            return coordinateY + step;
        }
    }

    public int defineNewCoordinateLeftDirectionForDoubleStep(int coordinateY, int step) {
        if (coordinateY == 0) {
            return cellInitializer.island.getCellCoordinateYLength() - 1 - step;
        } else if (coordinateY == 1) {
            return cellInitializer.island.getCellCoordinateYLength() - 1;
        } else {
            return coordinateY - step;
        }
    }
}
