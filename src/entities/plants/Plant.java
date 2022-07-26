package entities.plants;

public class Plant {
    private int coordinateX;
    private int coordinateY;
    private Double weight;

    public Plant(int coordinateX, int coordinateY, Double weight) {
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.weight = weight;
    }

    public int getCoordinateX() {
        return coordinateX;
    }

    public void setCoordinateX(int coordinateX) {
        this.coordinateX = coordinateX;
    }

    public int getCoordinateY() {
        return coordinateY;
    }

    public void setCoordinateY(int coordinateY) {
        this.coordinateY = coordinateY;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Plant{" +
               "coordinateX=" + coordinateX +
               ", coordinateY=" + coordinateY +
               ", weight=" + weight +
               '}';
    }
}
