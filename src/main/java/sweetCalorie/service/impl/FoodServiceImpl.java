package sweetCalorie.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sweetCalorie.constant.GlobalConstants;
import sweetCalorie.model.binding.FoodAddBindingModel;
import sweetCalorie.model.entity.Food;
import sweetCalorie.repository.FoodRepository;
import sweetCalorie.service.FoodService;
import sweetCalorie.util.ValidationUtil;

import java.io.FileReader;
import java.io.IOException;

@Service
public class FoodServiceImpl implements FoodService {
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final FoodRepository foodRepository;
    private final Gson gson;

    @Autowired
    public FoodServiceImpl(ModelMapper modelMapper, ValidationUtil validationUtil,
                           FoodRepository foodRepository, Gson gson) {
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.foodRepository = foodRepository;
        this.gson = gson;
    }

    @Override
    public boolean areImported() {
        return this.foodRepository.count() > 0;
    }


    @Override
    public void importFoods() throws IOException {
        FoodAddBindingModel[] foodAddBindingModels = gson.fromJson(
                new FileReader(GlobalConstants.FOODS_FILE_PATH), FoodAddBindingModel[].class);

        for (FoodAddBindingModel foodAdd : foodAddBindingModels) {
            if (this.validationUtil.isValid(foodAdd)) {
                if (this.foodRepository.findByName(foodAdd.getName()) == null) {
                    Food food = this.modelMapper.map(foodAdd, Food.class);
                    this.foodRepository.saveAndFlush(food);
                }
            }
        }
    }

    @Override
    public Food findByName(String name) {
        return this.foodRepository.findByName(name);
    }

    @Override
    public void addNewFood(String name, String category, String imageUrl,
                           double calories, double proteins, double carbohydrates,
                           double sugars, double fats) {
        Food food = new Food(name, category, imageUrl, calories,
                proteins, carbohydrates, sugars, fats);
    }
}
