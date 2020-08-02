package sweetCalorie.model.binding;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class IngredientBindingModel {
    private int quantity;
    private String food;
    private String units;

    public IngredientBindingModel() {
    }

    @NotNull
    @Positive
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @NotNull
    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }
}
