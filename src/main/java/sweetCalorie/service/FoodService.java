package sweetCalorie.service;

import sweetCalorie.model.entity.Food;
import sweetCalorie.model.entity.FoodCategory;
import sweetCalorie.model.service.FoodServiceModel;

import java.io.IOException;
import java.util.List;

public interface FoodService {

    boolean areImported();

    void importFoods() throws IOException;

    Food findByName(String name);

    void addNewFood(String name, String category, String imageUrl,
                    double calories, double proteins, double carbohydrates,
                    double sugars, double fats);

    List<FoodServiceModel> findAllFoods();

    List<FoodServiceModel> findAllFoodsByCategory(FoodCategory foodCategory);

    FoodServiceModel findById(String id);

    void deleteById(String id);
}
