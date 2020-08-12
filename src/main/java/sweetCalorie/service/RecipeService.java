package sweetCalorie.service;

import sweetCalorie.model.service.CommentServiceModel;
import sweetCalorie.model.service.RecipeServiceModel;

import java.io.IOException;
import java.util.List;

public interface RecipeService {

    boolean areImported();

    void importRecipes() throws IOException;

    RecipeServiceModel findById(String id);

    List<RecipeServiceModel> findAllRecipes();

    void addRecipe(RecipeServiceModel recipeServiceModel);

    void deleteById(String id);

    void addComment(RecipeServiceModel recipe, CommentServiceModel commentServiceModel);

    void deleteComment(RecipeServiceModel recipeServiceModel, CommentServiceModel commentServiceModel);

    void editRecipe(RecipeServiceModel recipeServiceModel);
}
