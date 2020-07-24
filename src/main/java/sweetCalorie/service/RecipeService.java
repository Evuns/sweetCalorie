package sweetCalorie.service;

import sweetCalorie.model.entity.Recipe;

import java.io.IOException;

public interface RecipeService {

    boolean areImported();

    void importRecipes() throws IOException;
}
