package sweetCalorie.model.service;

import org.hibernate.validator.constraints.Length;
import sweetCalorie.constant.GlobalConstants;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class CommentServiceModel {

    private String id;
    private UserServiceModel author;
    private String text;
    private Date postDateTime;
    private Date updateDateTime;
    private RecipeServiceModel recipeServiceModel;

    public CommentServiceModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UserServiceModel getAuthor() {
        return author;
    }

    public void setAuthor(UserServiceModel author) {
        this.author = author;
    }
    @NotEmpty(message = GlobalConstants.FIELD_NEEDED)
    @Length(min = 2, max = 500, message = GlobalConstants.COMMENT_LENGTH)
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getPostDateTime() {
        return postDateTime;
    }

    public void setPostDateTime(Date postDateTime) {
        this.postDateTime = postDateTime;
    }

    public Date getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime(Date updateDateTime) {
        this.updateDateTime = updateDateTime;
    }

    @NotNull(message = GlobalConstants.FIELD_NEEDED)
    public RecipeServiceModel getRecipeServiceModel() {
        return recipeServiceModel;
    }

    public void setRecipeServiceModel(RecipeServiceModel recipeServiceModel) {
        this.recipeServiceModel = recipeServiceModel;
    }
}
