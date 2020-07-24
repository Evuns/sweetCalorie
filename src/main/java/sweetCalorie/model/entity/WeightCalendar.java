package sweetCalorie.model.entity;

import org.hibernate.annotations.Formula;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "weigh_calendars")
public class WeightCalendar extends BaseEntity {

    private User user;
    private double goalWeight;
    private List<LocalDate> date;
    private List<Double> weight;
    private List<Double> progress;

    public WeightCalendar() {
       this.date = new LinkedList<>();
        this.weight = new LinkedList<>();
        this.progress = new LinkedList<>();
    }

    @OneToOne
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ElementCollection
    @PastOrPresent
    public List<LocalDate> getDate() {
        return date;
    }

    public void setDate(List<LocalDate> date) {
        this.date = date;
    }

    @Column(name = "goal_weight")
    @Min(0)
    public double getGoalWeight() {
        return goalWeight;
    }

    public void setGoalWeight(double goalWeight) {
        this.goalWeight = goalWeight;
    }

    @ElementCollection
    @Min(0)
    public List<Double> getWeight() {
        return weight;
    }

    public void setWeight(List<Double> weight) {
        this.weight = weight;
    }

    @ElementCollection
    public List<Double> getProgress() {
        return progress;
    }

    public void setProgress(List<Double> progress) {
        this.progress = progress;
    }
}
