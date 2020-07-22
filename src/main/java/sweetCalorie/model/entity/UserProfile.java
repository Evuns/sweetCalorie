package sweetCalorie.model.entity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "user_profiles")
public class UserProfile extends BaseEntity {

    private User user;
    private int height;
    private List<Double> weight;
    private String gender;
    private int age;
    private ActivityLevel activityLevel;

    public UserProfile() {
        this.weight = new LinkedList<>();
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

    @ElementCollection
    @Min(0)
    public List<Double> getWeight() {
        return weight;
    }

    public void setWeight(List<Double> weight) {
        this.weight = weight;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public ActivityLevel getActivityLevel() {
        return activityLevel;
    }

    public void setActivityLevel(ActivityLevel activityLevel) {
        this.activityLevel = activityLevel;
    }
}
