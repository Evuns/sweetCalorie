package sweetCalorie.model.entity;

import org.hibernate.annotations.Formula;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "user_profiles")
public class UserProfile extends BaseEntity {

    private User user;
    private int height;
    private WeightCalendar weightCalendar;
    private Gender gender;
    private LocalDate dateOfBirth;
    private int age;
    private ActivityLevel activityLevel;
    private List<Recipe> recipes;
    private List<Comment> comments;

    public UserProfile() {
    }

    @OneToOne(cascade = CascadeType.ALL)
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

    @OneToOne(cascade = CascadeType.ALL)
    public WeightCalendar getWeightCalendar() {
        return weightCalendar;
    }

    public void setWeightCalendar(WeightCalendar weightCalendar) {
        this.weightCalendar = weightCalendar;
    }

    @Enumerated
    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Column
    @Past
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Formula(value = "date_part('year', age(dateOfBirth))")
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
    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
