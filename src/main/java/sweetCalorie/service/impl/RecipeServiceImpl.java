package sweetCalorie.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import sweetCalorie.constant.GlobalConstants;
import sweetCalorie.model.binding.CommentAddBindingModel;
import sweetCalorie.model.binding.IngredientBindingModel;
import sweetCalorie.model.binding.RecipeAddBindingModel;
import sweetCalorie.model.entity.*;
import sweetCalorie.model.service.CommentServiceModel;
import sweetCalorie.model.service.IngredientServiceModel;
import sweetCalorie.model.service.RecipeServiceModel;
import sweetCalorie.model.service.UserProfileServiceModel;
import sweetCalorie.repository.RecipeRepository;
import sweetCalorie.service.*;
import sweetCalorie.util.ValidationUtil;

import java.io.FileReader;
import java.io.IOException;
import java.security.Principal;
import java.util.*;

@Service
public class RecipeServiceImpl implements RecipeService {

    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final RecipeRepository recipeRepository;
    private final Gson gson;
    private final IngredientService ingredientService;
    private final UserService userService;
    private final CommentService commentService;
    private final UserProfileService userProfileService;


    public RecipeServiceImpl(ModelMapper modelMapper,
                             ValidationUtil validationUtil,
                             RecipeRepository recipeRepository, Gson gson,
                             IngredientService ingredientService,
                             UserService userService,
                             CommentService commentService,
                             UserProfileService userProfileService) {
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.recipeRepository = recipeRepository;
        this.gson = gson;
        this.ingredientService = ingredientService;
        this.userService = userService;
        this.commentService = commentService;
        this.userProfileService = userProfileService;
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
                        Ingredient ingredient = this.modelMapper.map(ingredientBindingModel, Ingredient.class);
                        this.ingredientService.addNewIngredient(ingredient);
                        ingredients.add(ingredient);
                    }
                    recipe.setIngredients(ingredients);
                    recipe.setPostDate(new Date());
                    this.recipeRepository.saveAndFlush(recipe);
                }
            }
        }
    }

    @Override
    public RecipeServiceModel findById(String id) {
        return (RecipeServiceModel) this.recipeRepository.findById(id)
                .map(recipe -> {
                    IngredientBindingModel ingredientBindingModel = this.modelMapper
                            .map(recipe.getIngredients(), IngredientBindingModel.class);
                    return this.modelMapper.map(
                            recipe, RecipeServiceModel.class);
                }).orElse(null);
    }

    @Override
    public List<RecipeServiceModel> findAllRecipes() {
        List<RecipeServiceModel> allRecipes = new LinkedList<>();
        this.recipeRepository.findAll().forEach(recipe -> {
            RecipeServiceModel recipeServiceModel = modelMapper.map(recipe, RecipeServiceModel.class);
            allRecipes.add(recipeServiceModel);
        });
        allRecipes.sort(new RecipeServiceImpl.Sort());
        return allRecipes;
    }

    @Override
    public void addRecipe(RecipeServiceModel recipeServiceModel) {
        Recipe recipe = this.modelMapper.map(recipeServiceModel, Recipe.class);
        List<Ingredient> ingredients = new LinkedList<>();
        for (int i = 0; i < recipeServiceModel.getIngredients().size(); i++) {
            IngredientServiceModel ingredientServiceModel = recipeServiceModel.getIngredients().get(i);
            Ingredient ingredient = this.modelMapper.map(ingredientServiceModel, Ingredient.class);
            this.ingredientService.addNewIngredient(ingredient);
            ingredients.add(ingredient);
        }
        recipe.setIngredients(ingredients);
        recipe.setPostDate(new Date());
        this.recipeRepository.saveAndFlush(recipe);
    }

    @Override
    public void deleteById(String id) {
        if (this.recipeRepository.findById(id).isPresent()) {
            this.recipeRepository.deleteById(id);
        }
    }

    @Override
    public CommentServiceModel addComment(CommentAddBindingModel commentAddBindingModel,
                           RecipeServiceModel recipe, Principal principal) {
        CommentServiceModel commentServiceModel = this.modelMapper
                .map(commentAddBindingModel, CommentServiceModel.class);

        commentServiceModel.setAuthor(
                this.userService.findByUsername(principal.getName()));

        commentServiceModel.setPostDateTime(new Date());
        if (recipe != null) {
            commentServiceModel.setRecipeServiceModel(recipe);
            this.commentService.createComment(commentServiceModel);
            Recipe r = this.recipeRepository.findById(recipe.getId()).orElse(null);
            Comment comment = this.modelMapper.map(commentServiceModel, Comment.class);
            r.getComments().add(comment);
            r.setComments(r.getComments());
        }
        return commentServiceModel;
    }

    @Override
    public void deleteComment(RecipeServiceModel recipeServiceModel, CommentServiceModel commentServiceModel) {
        Recipe r = this.recipeRepository.findById(recipeServiceModel.getId()).orElse(null);
        if (r != null) {
            List<Comment> newComments = r.getComments();
            for (Comment comment : r.getComments()) {
                if (comment.getId().equals(commentServiceModel.getId())) {
                    newComments.remove(comment);
                    break;
                }
            }
            r.setComments(newComments);
        }
    }

    @Override
    public void editRecipe(RecipeServiceModel recipeServiceModel) {
        Recipe recipe = this.recipeRepository.findById(
                recipeServiceModel.getId()).orElse(null);
        if (recipe != null) {
            recipe.setTitle(recipeServiceModel.getTitle());
            recipe.setImage(recipeServiceModel.getImage());
            recipe.setCalories(recipeServiceModel.getCalories());
            recipe.setCarbohydrates(recipeServiceModel.getCarbohydrates());
            recipe.setFats(recipeServiceModel.getFats());
            recipe.setProteins(recipeServiceModel.getProteins());
            recipe.setTime(recipeServiceModel.getTime());
            recipe.setServings(recipeServiceModel.getServings());
            recipe.setDifficulty(recipeServiceModel.getDifficulty());
            recipe.setDescription(recipeServiceModel.getDescription());
            recipe.setUpdateDate(new Date());

            for (int i = 0; i < recipeServiceModel.getIngredients().size(); i++) {
                IngredientServiceModel ingredientServiceModel = recipeServiceModel.getIngredients().get(i);
                Ingredient ingredient = recipe.getIngredients().get(i);
                if (!ingredientServiceModel.getFood().equals(ingredient.getFood()) ||
                        ingredientServiceModel.getQuantity() != ingredient.getQuantity() ||
                        !ingredientServiceModel.getUnits().equals(ingredient.getUnits())) {
                    ingredient.setFood(ingredientServiceModel.getFood());
                    ingredient.setQuantity(ingredientServiceModel.getQuantity());
                    ingredient.setUnits(ingredientServiceModel.getUnits());
                }
            }
            this.recipeRepository.saveAndFlush(recipe);
        }
    }

    static class Sort implements Comparator<RecipeServiceModel> {
        @Override
        public int compare(RecipeServiceModel o1, RecipeServiceModel o2) {
            return o1.getPostDate().compareTo(o2.getPostDate());
        }
    }

}


