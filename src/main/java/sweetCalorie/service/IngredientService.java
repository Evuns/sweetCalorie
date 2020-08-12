package sweetCalorie.service;

import sweetCalorie.model.entity.Ingredient;

public interface IngredientService {
    void addNewIngredient(Ingredient ingredient);

    void deleteById(String id);
}
