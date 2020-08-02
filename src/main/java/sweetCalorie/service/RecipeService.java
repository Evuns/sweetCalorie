package sweetCalorie.service;

import sweetCalorie.model.service.RecipeServiceModel;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface RecipeService {

    boolean areImported();

    void importRecipes() throws IOException;

    RecipeServiceModel findById(String id);

    List<RecipeServiceModel> findAllRecipes();
}
