package sweetCalorie.service;

import sweetCalorie.model.binding.CommentAddBindingModel;
import sweetCalorie.model.service.CommentServiceModel;
import sweetCalorie.model.service.RecipeServiceModel;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

public interface RecipeService {

    boolean areImported();

    void importRecipes() throws IOException;

    RecipeServiceModel findById(String id);

    List<RecipeServiceModel> findAllRecipes();

    void addRecipe(RecipeServiceModel recipeServiceModel);

    void deleteById(String id);

    CommentServiceModel addComment(CommentAddBindingModel commentAddBindingModel, RecipeServiceModel recipe, Principal principal);

    void deleteComment(RecipeServiceModel recipeServiceModel, CommentServiceModel commentServiceModel);

    void editRecipe(RecipeServiceModel recipeServiceModel);
}
