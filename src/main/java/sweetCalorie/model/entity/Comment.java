package sweetCalorie.model.entity;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "comments")
public class Comment extends BaseEntity {

    private User author;
    private String text;

    public Comment() {
    }

    @ManyToOne
    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    @Column(nullable = false)
    @Length(max = 500)
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
