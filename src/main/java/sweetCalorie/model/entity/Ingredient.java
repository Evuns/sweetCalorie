package sweetCalorie.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ingredients")
public class Ingredient extends BaseEntity {

    private int quantity;
    private String food;
    private String units;

    public Ingredient() {
    }

    @Column(nullable = false)
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Column(nullable = false)
    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    @Column(nullable = false)
    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }
}
