package sweetCalorie.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import sweetCalorie.model.entity.Recipe;
import sweetCalorie.model.service.RecipeServiceModel;
import sweetCalorie.repository.RecipeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RecipeServiceImplTest{

    @InjectMocks
    private RecipeServiceImpl recipeService;


    @Mock
    private RecipeRepository recipeRepository;

    @Mock
    private ModelMapper modelMapper;

    private Recipe recipe;
    private RecipeServiceModel recipeServiceModel;

    @BeforeEach
    public void init() {

        recipe = new Recipe("Recipe name", "description how to make recipe properly",
                "someImage", 50,
        20, 30, 10, 30, 2,
       "easy");
        recipe.setId("1");


        recipeServiceModel = new RecipeServiceModel();
        recipeServiceModel.setId("1");
        recipeServiceModel.setTitle("Recipe name");
        recipeServiceModel.setImage("image2");
        recipeServiceModel.setDifficulty("moderate");
        recipeServiceModel.setServings(3);
        recipeServiceModel.setTime(30);
        recipeServiceModel.setDescription("description how to make recipe properly");
    }

    @Test
    void areImported_ReturnFalseWhenIsEmpty() {
        long i = this.recipeRepository.count();
        assertEquals(0,i);
    }

    @Test
    void findById_shouldReturnRecipe() {
        Mockito.when(recipeRepository.findById("1")).thenReturn(Optional.of(recipe));
        Mockito.when(modelMapper.map(recipe, RecipeServiceModel.class)).thenReturn(recipeServiceModel);
        RecipeServiceModel actual = recipeService.findById("1");
        assertEquals(recipeServiceModel, actual);
    }

    @Test
    void findAllRecipes_shouldReturnAllRecipes() {
        List<Recipe> recipes = new ArrayList<>();
        recipes.add(recipe);
        Mockito.when(recipeRepository.findAll()).thenReturn(recipes);
        Mockito.when(modelMapper.map(recipeRepository.findAll(), RecipeServiceModel[].class))
                .thenReturn(new RecipeServiceModel[]{recipeServiceModel});

        List<RecipeServiceModel> actual = recipeService.findAllRecipes();
        assertEquals(1, actual.size());
    }

    @Test
    void deleteById_shouldDeleteRecipeFromDB() {
        Mockito.when(recipeRepository.findById("1")).thenReturn(Optional.of(recipe));
        recipeRepository.deleteById(recipe.getId());
        assertThat(recipeRepository.count()).isEqualTo(0);
    }
}