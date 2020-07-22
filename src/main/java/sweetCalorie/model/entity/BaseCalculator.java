package sweetCalorie.model.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@MappedSuperclass
public abstract class BaseCalculator extends BaseEntity {

    private double weight;
    private int height;

    public BaseCalculator() {
    }

    @Column(nullable = false)
    @Min(0)
    @Max(300)
    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Column(nullable = false)
    @Min(0)
    @Max(300)
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
