package sweetCalorie.service;

import sweetCalorie.model.entity.Food;

import java.io.IOException;

public interface FoodService {

    boolean areImported();

    void importFoods() throws IOException;

    Food findByName(String name);

    void addNewFood(String name, String category, String imageUrl,
                    double calories, double proteins, double carbohydrates,
                    double sugars, double fats);
}
