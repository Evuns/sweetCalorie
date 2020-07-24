package sweetCalorie.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "comments")
public class Comment extends BaseEntity {

    private User author;
    private String text;
    private Date postDateTime;
    private Date updateDateTime;

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
    @Length(min = 2, max = 500)
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Column(name = "post_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getPostDateTime() {
        return postDateTime;
    }

    public void setPostDateTime (Date postDateTime) {
        this.postDateTime = postDateTime;
    }

    @Column(name = "update_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime(Date updateDateTime) {
        this.updateDateTime = updateDateTime;
    }
}
