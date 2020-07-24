package sweetCalorie.init;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import sweetCalorie.service.FoodService;
import sweetCalorie.service.RecipeService;

@Component
public class DataInitializer implements CommandLineRunner {

    private final FoodService foodService;
    private final RecipeService recipeService;

    public DataInitializer(FoodService foodService, RecipeService recipeService) {
        this.foodService = foodService;
        this.recipeService = recipeService;
    }

    @Override
    public void run(String... args) throws Exception {
        if(!foodService.areImported()) {
           foodService.importFoods();
        }
        if(!recipeService.areImported()) {
            recipeService.importRecipes();
        }
    }
}
