package sweetCalorie.service.impl;

import org.springframework.stereotype.Service;
import sweetCalorie.model.entity.Ingredient;
import sweetCalorie.repository.IngredientRepository;
import sweetCalorie.service.IngredientService;

@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;

    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public void addNewIngredient(Ingredient ingredient) {
        this.ingredientRepository.saveAndFlush(ingredient);
    }

    @Override
    public void deleteById(String id) {
        this.ingredientRepository.deleteById(id);
    }
}
