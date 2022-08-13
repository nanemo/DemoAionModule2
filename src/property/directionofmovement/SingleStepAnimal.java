package property.directionofmovement;

import controller.CellInitializer;

public class SingleStepAnimal {
    private CellInitializer cellInitializer = new CellInitializer();

    public int defineNewCoordinateStraightDirectionForSingleStep(int coordinateX, int step) {
        if (coordinateX == 0) {
            return cellInitializer.island.getCellCoordinateXLength() - 1;
        } else {
            return coordinateX - step;
        }
    }

    public int defineNewCoordinateBackDirectionForSingleStep(int coordinateX, int step) {
        if (coordinateX == cellInitializer.island.getCellCoordinateXLength() - 1) {
            return step;
        } else {
            return coordinateX + step;
        }
    }

    public int defineNewCoordinateRightDirectionForSingleStep(int coordinateY, int step) {
        if (coordinateY == cellInitializer.island.getCellCoordinateYLength() - 1) {
            return step;
        } else {
            return coordinateY + step;
        }
    }

    public int defineNewCoordinateLeftDirectionForSingleStep(int coordinateY, int step) {
        if (coordinateY == 0) {
            return cellInitializer.island.getCellCoordinateYLength() - 1;
        } else {
            return coordinateY - step;
        }
    }
}
