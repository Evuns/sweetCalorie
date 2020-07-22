package sweetCalorie.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ingredients")
public class Ingredient extends BaseEntity {

    private int quantity;
    private Food food;

    public Ingredient() {
    }

    @Column(nullable = false)
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @OneToOne
    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }
}
