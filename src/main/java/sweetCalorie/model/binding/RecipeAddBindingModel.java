package sweetCalorie.model.binding;

import org.hibernate.validator.constraints.Length;
import sweetCalorie.model.entity.Comment;
import sweetCalorie.model.entity.Ingredient;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

public class RecipeAddBindingModel {

    private String title;
    private String description;
    private String imageUrl;
    private List<IngredientBindingModel> ingredients;
    private int calories;
    private int carbohydrates;
    private int fats;
    private int proteins;
    private int time;
    private int servings;
    private String difficulty;

    public RecipeAddBindingModel() {
    }

    @NotNull
    @Length(min = 3)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @NotNull
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @NotNull
    public List<IngredientBindingModel> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<IngredientBindingModel> ingredients) {
        this.ingredients = ingredients;
    }

    @NotNull
    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    @NotNull
    public int getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(int carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    @NotNull
    public int getFats() {
        return fats;
    }

    public void setFats(int fats) {
        this.fats = fats;
    }

    @NotNull
    public int getProteins() {
        return proteins;
    }

    public void setProteins(int proteins) {
        this.proteins = proteins;
    }

    @NotNull
    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    @NotNull
    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    @NotNull
    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }
}
