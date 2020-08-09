package sweetCalorie.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;

@Entity
@Table(name = "foods")
public class Food extends BaseEntity {

    private String name;
    private FoodCategory category;
    private String image;
    private double calories;
    private double proteins;
    private double carbohydrates;
    private double sugars;
    private double fats;

    public Food() {
    }

    public Food( String name, String category, String image,
                 double calories, double proteins, double carbohydrates,
                 double sugars, double fats){
        this.name = name;
        this.category = FoodCategory.valueOf(category.toUpperCase());
        this.image = image;
        this.calories = calories;
        this.proteins = proteins;
        this.carbohydrates = carbohydrates;
        this.sugars = sugars;
        this.fats = fats;
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

    @Column()
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Column(nullable = false)
    @Min(0)
    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    @Column(nullable = false)
    @Min(0)
    public double getProteins() {
        return proteins;
    }

    public void setProteins(double proteins) {
        this.proteins = proteins;
    }

    @Column(nullable = false)
    @Min(0)
    public double getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(double carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    @Column(nullable = false)
    @Min(0)
    public double getFats() {
        return fats;
    }

    public void setFats(double fats) {
        this.fats = fats;
    }

    @Column
    @Min(0)
    public double getSugars() {
        return sugars;
    }

    public void setSugars(double sugars) {
        this.sugars = sugars;
    }
}
