package sweetCalorie.model.service;

import org.hibernate.validator.constraints.Length;
import sweetCalorie.constant.GlobalConstants;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class RecipeServiceModel {

    private String id;
    private String title;
    private String description;
    private String image;
    private Date postDate;
    private LocalDate updateDate;
    private List<IngredientServiceModel> ingredients;
    private int calories;
    private int carbohydrates;
    private int fats;
    private int proteins;
    private int time;
    private int servings;
    private String difficulty;
    private List<CommentServiceModel> comments;


    public RecipeServiceModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @NotEmpty(message = GlobalConstants.TITLE_NEEDED)
    @Length(min = 3, message = GlobalConstants.TITLE_LENGTH)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @NotEmpty(message = GlobalConstants.DESCRIPTION_NEEDED)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @NotEmpty(message = GlobalConstants.INGREDIENTS_NEEDED)
    public List<IngredientServiceModel> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<IngredientServiceModel> ingredients) {
        this.ingredients = ingredients;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public LocalDate getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDate updateDate) {
        this.updateDate = updateDate;
    }

    public List<CommentServiceModel> getComments() {
        return comments;
    }

    public void setComments(List<CommentServiceModel> comments) {
        this.comments = comments;
    }

    @NotNull(message = GlobalConstants.FIELD_NEEDED)
    @Min(value = 0, message = "Калорийната стойност " + GlobalConstants.POSITIVE_NUMBER)
    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    @NotNull(message = GlobalConstants.FIELD_NEEDED)
    @Min(value = 0, message = "Стойността на въглехидратите " + GlobalConstants.POSITIVE_NUMBER)
    public int getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(int carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    @NotNull(message = GlobalConstants.FIELD_NEEDED)
    @Min(value = 0, message = "Стойността на мазнините " + GlobalConstants.POSITIVE_NUMBER)
    public int getFats() {
        return fats;
    }

    public void setFats(int fats) {
        this.fats = fats;
    }

    @NotNull(message = GlobalConstants.FIELD_NEEDED)
    @Min(value = 0, message = "Стойността на протеините " + GlobalConstants.POSITIVE_NUMBER)
    public int getProteins() {
        return proteins;
    }

    public void setProteins(int proteins) {
        this.proteins = proteins;
    }

    @NotNull(message = GlobalConstants.FIELD_NEEDED)
    @Min(value = 1, message = "Времето за приготвяне " + GlobalConstants.POSITIVE_NUMBER)
    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    @NotNull(message = GlobalConstants.FIELD_NEEDED)
    @Min(value = 0, message = "Броят на порциите " + GlobalConstants.POSITIVE_NUMBER)
    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    @NotEmpty(message = GlobalConstants.FIELD_NEEDED)
    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }
}
