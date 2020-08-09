package sweetCalorie.model.service;

import org.hibernate.validator.constraints.Length;
import sweetCalorie.constant.GlobalConstants;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class FoodServiceModel {
    private String id;
    private String name;
    private String category;
    private String image;
    private double calories;
    private double proteins;
    private double carbohydrates;
    private double sugars;
    private double fats;

    public FoodServiceModel() {
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @NotNull(message = GlobalConstants.FIELD_NEEDED)
    @Length(min = 2, max = 20, message = GlobalConstants.FIELD_LENGTH)
    @Pattern(regexp = "^\\p{L}+\\s*\\p{L}+\\s*\\p{L}+$", message = GlobalConstants.FIELD_PATTERN)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull(message = GlobalConstants.FIELD_NEEDED)
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @NotNull(message = GlobalConstants.FIELD_NEEDED)
    @Min(value = 0, message = "Полето с калории " + GlobalConstants.POSITIVE_NUMBER)
    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    @NotNull(message = GlobalConstants.FIELD_NEEDED)
    @Min(value = 0, message = "Полето с протеини " + GlobalConstants.POSITIVE_NUMBER)
    public double getProteins() {
        return proteins;
    }

    public void setProteins(double proteins) {
        this.proteins = proteins;
    }

    @NotNull(message = GlobalConstants.FIELD_NEEDED)
    @Min(value = 0, message = "Полето с въглехидрати " + GlobalConstants.POSITIVE_NUMBER)
    public double getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(double carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    @NotNull(message = GlobalConstants.FIELD_NEEDED)
    @Min(value = 0, message = "Полето със захари " + GlobalConstants.POSITIVE_NUMBER)
    public double getSugars() {
        return sugars;
    }

    public void setSugars(double sugars) {
        this.sugars = sugars;
    }

    @NotNull(message = GlobalConstants.FIELD_NEEDED)
    @Min(value = 0, message = "Полето с мазнини " + GlobalConstants.POSITIVE_NUMBER)
    public double getFats() {
        return fats;
    }

    public void setFats(double fats) {
        this.fats = fats;
    }
}
