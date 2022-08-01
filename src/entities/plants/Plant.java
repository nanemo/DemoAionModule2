package entities.plants;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Plant  {
    private Double weight;

    public Plant(Double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Plant{" +
               "weight=" + weight +
               '}';
    }
}
