package sweetCalorie.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import sweetCalorie.model.entity.*;
import sweetCalorie.model.service.*;
import sweetCalorie.repository.UserProfileRepository;
import sweetCalorie.service.UserProfileService;

import java.util.List;

@Service
public class UserProfileServiceImpl implements UserProfileService {
    private final UserProfileRepository userProfileRepository;
    private final ModelMapper modelMapper;

    public UserProfileServiceImpl(UserProfileRepository userProfileRepository,
                                  ModelMapper modelMapper) {
        this.userProfileRepository = userProfileRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addFavouriteRecipe(UserServiceModel userServiceModel,
                                   RecipeServiceModel recipeServiceModel) {
        User user = this.modelMapper.map(userServiceModel, User.class);

        UserProfile userProfile = this.userProfileRepository.findByUser(user).orElse(null);
        List<Recipe> recipes = userProfile.getRecipes();
        for (Recipe recipe : recipes) {
            if(recipe.getId().equals(recipeServiceModel.getId())){
                return;
            }
        }
        recipes.add(modelMapper.map(recipeServiceModel, Recipe.class));
        userProfile.setRecipes(recipes);
        this.userProfileRepository.saveAndFlush(userProfile);
    }

    @Override
    public void addFavouriteFood(UserServiceModel userServiceModel,
                                 FoodServiceModel foodServiceModel) {
        User user = this.modelMapper.map(userServiceModel, User.class);

        UserProfile userProfile = this.userProfileRepository.findByUser(user).orElse(null);
        List<Food> foods = userProfile.getFoods();
        for (Food food : foods) {
            if(food.getId().equals(foodServiceModel.getId())){
                return;
            }
        }
        foods.add(modelMapper.map(foodServiceModel, Food.class));
        userProfile.setFoods(foods);
        this.userProfileRepository.saveAndFlush(userProfile);
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public List<RecipeServiceModel> findAllRecipes(String id) {
        return null;
    }

    @Override
    public List<FoodServiceModel> findAllFoods(String id) {
        return null;
    }

    @Override
    public UserProfileServiceModel findByUser(User user) {
        return this.modelMapper.map(this.userProfileRepository
                .findByUser(user).orElse(null), UserProfileServiceModel.class);
    }
}
