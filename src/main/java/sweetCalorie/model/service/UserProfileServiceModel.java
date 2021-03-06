package sweetCalorie.model.service;

import sweetCalorie.model.entity.*;
import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.List;

public class UserProfileServiceModel {

    private String id;
    private User user;
    private int height;
//    private WeightCalendar weightCalendar;
    private Gender gender;
//    private LocalDate dateOfBirth;
    private int age;
    private ActivityLevel activityLevel;
    private List<Recipe> recipes;
    private List<Food> foods;

    public UserProfileServiceModel() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "users_id",
            referencedColumnName = "id"
    )
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Column
    @Min(0)
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

//    @OneToOne(cascade = CascadeType.ALL)
//    public WeightCalendar getWeightCalendar() {
//        return weightCalendar;
//    }
//
//    public void setWeightCalendar(WeightCalendar weightCalendar) {
//        this.weightCalendar = weightCalendar;
//    }

    @Enumerated
    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

//    @Column
//    @Past
//    public LocalDate getDateOfBirth() {
//        return dateOfBirth;
//    }
//
//    public void setDateOfBirth(LocalDate dateOfBirth) {
//        this.dateOfBirth = dateOfBirth;
//    }


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Enumerated
    public ActivityLevel getActivityLevel() {
        return activityLevel;
    }

    public void setActivityLevel(ActivityLevel activityLevel) {
        this.activityLevel = activityLevel;
    }

    @OneToMany
    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    @OneToMany
    public List<Food> getFoods() {
        return foods;
    }

    public void setFoods(List<Food> foods) {
        this.foods = foods;
    }

}
