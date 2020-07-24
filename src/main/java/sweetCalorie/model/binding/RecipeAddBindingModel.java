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

    public List<IngredientBindingModel> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<IngredientBindingModel> ingredients) {
        this.ingredients = ingredients;
    }
}
