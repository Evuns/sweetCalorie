package sweetCalorie.model.binding;

import org.hibernate.validator.constraints.Length;
import sweetCalorie.constant.GlobalConstants;
import sweetCalorie.model.entity.User;

import java.util.Date;

public class CommentAddBindingModel {

    private User author;
    private String text;


    public CommentAddBindingModel() {
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    @Length(min = 2, max = 500, message = GlobalConstants.COMMENT_LENGTH)
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
