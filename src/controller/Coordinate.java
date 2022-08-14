package controller;

import lombok.Getter;
import lombok.Setter;
/**We use this class for defining coordinates for cells*/
@Getter
@Setter
public class Coordinate {
    private int coordinateX;
    private int coordinateY;

    public Coordinate(){

    }

    public Coordinate(int coordinateX, int coordinateY) {
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
    }

}
