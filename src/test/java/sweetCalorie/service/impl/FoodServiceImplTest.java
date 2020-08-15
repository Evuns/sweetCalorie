package sweetCalorie.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import sweetCalorie.model.entity.Food;
import sweetCalorie.model.service.FoodServiceModel;
import sweetCalorie.repository.FoodRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FoodServiceImplTest {

    @InjectMocks
    private FoodServiceImpl foodService;


    @Mock
    private FoodRepository foodRepository;

    @Mock
    private ModelMapper modelMapper;

    private Food food;
    private FoodServiceModel foodServiceModel;

    @BeforeEach
    public void init() {

        food = new Food("apple", "ПЛОДОВЕ", "someImage",
                20, 10, 15,
                2, 2);
        food.setId("1");


        foodServiceModel = new FoodServiceModel();
        foodServiceModel.setId("1");
        foodServiceModel.setCategory("ПЛОДОВЕ");
        foodServiceModel.setImage("image2");
    }

    @Test
    void findByName_shouldReturnFood() {
        Mockito.when(foodRepository.findByName("apple")).thenReturn(food);
        Food actual = foodService.findByName("apple");
        assertEquals(food, actual);
    }

    @Test
    void findAllFoods_shouldReturnAllFoods() {
        List<Food> foods = new ArrayList<>();
        foods.add(food);
        Mockito.when(foodRepository.findAll()).thenReturn(foods);
        Mockito.when(modelMapper.map(foodRepository.findAll(), FoodServiceModel[].class))
                .thenReturn(new FoodServiceModel[]{foodServiceModel});

        List<FoodServiceModel> actual = foodService.findAllFoods();
        assertEquals(1, actual.size());
    }

    @Test
    void findAllFoodsByCategory_shouldReturnOnlyFoodsFromCategoryGiven() {
        List<Food> foods = new ArrayList<>();
        foods.add(food);
        Food food2 = new Food("milk", "МЛЕЧНИ", "someImage",
                20, 10, 15,
                2, 2);
        food2.setId("2");
        foods.add(food2);
        Mockito.when(foodRepository.findAllByCategory(food.getCategory())).thenReturn(foods.subList(0, 1));
        Mockito.when(modelMapper.map(foodRepository.findAllByCategory(food.getCategory()), FoodServiceModel[].class))
                .thenReturn(new FoodServiceModel[]{foodServiceModel});
        List<FoodServiceModel> actual = foodService.findAllFoodsByCategory(food.getCategory());
        assertEquals(1, actual.size());
    }


    @Test
    void findById_shouldReturnFood() {
        Mockito.when(foodRepository.findById("1")).thenReturn(Optional.of(food));
        Mockito.when(modelMapper.map(food, FoodServiceModel.class)).thenReturn(foodServiceModel);
        FoodServiceModel actual = foodService.findById("1");
        assertEquals(foodServiceModel, actual);
    }

    @Test
    void deleteById_shouldDeleteFood() {
        Mockito.when(foodRepository.findById("1")).thenReturn(Optional.of(food));
        foodRepository.deleteById(food.getId());
        assertThat(foodRepository.count()).isEqualTo(0);
    }

    @Test
    void editFood_shouldEditFoodInDB() {
        Mockito.when(foodRepository.findById("1")).thenReturn(Optional.of(food));
        Mockito.when(modelMapper.map(food, FoodServiceModel.class)).thenReturn(foodServiceModel);
        foodServiceModel.setName("apple_test");
        foodService.editFood(foodServiceModel);
        FoodServiceModel actual = foodService.findById("1");
        assertEquals(foodServiceModel, actual);
    }
}