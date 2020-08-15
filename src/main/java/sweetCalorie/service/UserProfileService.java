package sweetCalorie.service;

import sweetCalorie.model.entity.User;
import sweetCalorie.model.service.*;

import java.util.List;

public interface UserProfileService {

    void addFavouriteRecipe(UserServiceModel userServiceModel,
                            RecipeServiceModel recipeServiceModel);

    void addFavouriteFood(UserServiceModel userServiceModel, FoodServiceModel foodServiceModel);

    UserProfileServiceModel findByUser(User user);
}
