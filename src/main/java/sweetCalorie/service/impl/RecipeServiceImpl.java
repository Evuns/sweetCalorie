package sweetCalorie.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import sweetCalorie.constant.GlobalConstants;
import sweetCalorie.model.binding.IngredientBindingModel;
import sweetCalorie.model.binding.RecipeAddBindingModel;
import sweetCalorie.model.entity.Food;
import sweetCalorie.model.entity.Ingredient;
import sweetCalorie.model.entity.Recipe;
import sweetCalorie.repository.RecipeRepository;
import sweetCalorie.service.FoodService;
import sweetCalorie.service.IngredientService;
import sweetCalorie.service.RecipeService;
import sweetCalorie.util.ValidationUtil;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@Service
public class RecipeServiceImpl implements RecipeService {

    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final RecipeRepository recipeRepository;
    private final Gson gson;
    private final FoodService foodService;
    private final IngredientService ingredientService;

    public RecipeServiceImpl(ModelMapper modelMapper,
                             ValidationUtil validationUtil,
                             RecipeRepository recipeRepository, Gson gson,
                             FoodService foodService, IngredientService ingredientService) {
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.recipeRepository = recipeRepository;
        this.gson = gson;
        this.foodService = foodService;
        this.ingredientService = ingredientService;
    }

    @Override
    public boolean areImported() {
        return this.recipeRepository.count() > 0;
    }

    @Override
    public void importRecipes() throws IOException {
        RecipeAddBindingModel[] recipeAddBindingModels = gson.fromJson(
                new FileReader(GlobalConstants.RECIPES_FILE_PATH), RecipeAddBindingModel[].class);

        for (RecipeAddBindingModel recipeAdd : recipeAddBindingModels) {
            if (this.validationUtil.isValid(recipeAdd)) {
                if (this.recipeRepository.findByTitle(recipeAdd.getTitle()) == null) {
                    Recipe recipe = this.modelMapper.map(recipeAdd, Recipe.class);
                    List<Ingredient> ingredients = new LinkedList<>();
                    for (int i = 0; i < recipeAdd.getIngredients().size(); i++) {
                        IngredientBindingModel ingredientBindingModel = recipeAdd.getIngredients().get(i);
                        Food food = this.foodService.findByName(
                                ingredientBindingModel.getFood());
                        if (food != null) {
                            Ingredient ingredient = this.modelMapper.map(ingredientBindingModel, Ingredient.class);
                            ingredient.setFood(food);
                            this.ingredientService.addNewIngredient(ingredient);
                            ingredients.add(ingredient);
                        } else {
                            //ToDo
                            //  this.foodService.addNewFood();
                        }
                    }
                    recipe.setIngredients(ingredients);
                    recipe.setPostDate(LocalDate.now());
                    this.recipeRepository.saveAndFlush(recipe);
                }
            }
        }

    }
}

