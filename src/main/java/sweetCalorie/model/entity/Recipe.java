package sweetCalorie.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "recipes")
public class Recipe extends BaseEntity {


    private String title;
    private String description;
    private String image;
    private Date postDate;
    private Date updateDate;
    private List<Ingredient> ingredients;
    private int calories;
    private int carbohydrates;
    private int fats;
    private int proteins;
    private int time;
    private int servings;
    private String difficulty;
    private List<Comment> comments;

    public Recipe() {
        this.comments = new ArrayList<>();
        this.ingredients = new ArrayList<>();
    }

    public Recipe( String title, String description, String image, int calories,
           int carbohydrates, int fats, int proteins, int time, int servings,
           String difficulty) {
        this.title = title;
        this.description = description;
        this.image = image;
        this.postDate = new Date();
        this.calories = calories;
        this.carbohydrates = carbohydrates;
        this.fats = fats;
        this.proteins = proteins;
        this.time = time;
        this.servings = servings;
        this.difficulty = difficulty;
        this.comments = new ArrayList<>();
        this.ingredients = new ArrayList<>();
    }

    @Column(nullable = false, unique = true)
    @Length(min = 3)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(nullable = false, columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "image")
    public String getImage() {
        return image;
    }

    public void setImage(String imageUrl) {
        this.image = imageUrl;
    }

    @Column(name = "post_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    @Column(name = "update_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @OneToMany
    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "recipe", orphanRemoval = true)
    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Column(nullable = false)
    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    @Column(nullable = false)
    public int getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(int carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    @Column(nullable = false)
    public int getFats() {
        return fats;
    }

    public void setFats(int fats) {
        this.fats = fats;
    }

    @Column(nullable = false)
    public int getProteins() {
        return proteins;
    }

    public void setProteins(int proteins) {
        this.proteins = proteins;
    }

    @Column(nullable = false)
    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    @Column(nullable = false)
    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    @Column(nullable = false)
    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }
}
