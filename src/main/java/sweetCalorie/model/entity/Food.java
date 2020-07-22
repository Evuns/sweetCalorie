package sweetCalorie.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.Min;

@Entity
@Table(name = "foods")
public class Food extends BaseEntity {

    private String name;
    private FoodCategory category;
    private int calories;
    private int proteins;
    private int carbohydrates;
    private int fibers;
    private int sugars;
    private int fats;

    public Food() {
    }

    @Column(nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(nullable = false)
    @Enumerated
    public FoodCategory getCategory() {
        return category;
    }

    public void setCategory(FoodCategory category) {
        this.category = category;
    }

    @Column(nullable = false)
    @Min(0)
    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    @Column(nullable = false)
    @Min(0)
    public int getProteins() {
        return proteins;
    }

    public void setProteins(int proteins) {
        this.proteins = proteins;
    }

    @Column(nullable = false)
    @Min(0)
    public int getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(int carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    @Column(nullable = false)
    @Min(0)
    public int getFats() {
        return fats;
    }

    public void setFats(int fats) {
        this.fats = fats;
    }

    @Column
    @Min(0)
    public int getSugars() {
        return sugars;
    }

    public void setSugars(int sugars) {
        this.sugars = sugars;
    }

    @Column
    @Min(0)
    public int getFibers() {
        return fibers;
    }

    public void setFibers(int fibers) {
        this.fibers = fibers;
    }

}
