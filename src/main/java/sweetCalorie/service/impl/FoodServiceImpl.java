package sweetCalorie.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sweetCalorie.constant.GlobalConstants;
import sweetCalorie.model.binding.FoodAddBindingModel;
import sweetCalorie.model.entity.Food;
import sweetCalorie.model.entity.FoodCategory;
import sweetCalorie.model.service.FoodServiceModel;
import sweetCalorie.repository.FoodRepository;
import sweetCalorie.service.FoodService;
import sweetCalorie.util.ValidationUtil;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

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
                if (this.foodRepository.findByName(foodAdd.getName().toLowerCase()) == null) {
                    Food food = this.modelMapper.map(foodAdd, Food.class);
                    food.setName(foodAdd.getName().toLowerCase());
                    this.foodRepository.saveAndFlush(food);
                }
            }
        }
    }

    @Override
    public Food findByName(String name) {
        return this.foodRepository.findByName(name.toLowerCase());
    }

//    @Override
//    public void addNewFood(String name, String category, String image,
//                           double calories, double proteins, double carbohydrates,
//                           double sugars, double fats) {
//        Food food = new Food(name, category, image, calories,
//                proteins, carbohydrates, sugars, fats);
//    }

    @Override
    public List<FoodServiceModel> findAllFoods() {
        List<FoodServiceModel> allFoods = new LinkedList<>();
        this.foodRepository.findAll().forEach(food -> {
            FoodServiceModel foodServiceModel = modelMapper.map(food, FoodServiceModel.class);
            allFoods.add(foodServiceModel);
        });
        allFoods.sort(new Sort());
        return allFoods;
    }

    @Override
    public List<FoodServiceModel> findAllFoodsByCategory(FoodCategory foodCategory) {
        List<FoodServiceModel> allFoods = new LinkedList<>();
        this.foodRepository.findAllByCategory(foodCategory).forEach(food -> {
            FoodServiceModel foodServiceModel = modelMapper.map(food, FoodServiceModel.class);
            allFoods.add(foodServiceModel);
        });
        allFoods.sort(new Sort());
        return allFoods;
    }

    @Override
    public FoodServiceModel findById(String id) {
        return (FoodServiceModel) this.foodRepository.findById(id)
                .map(food -> {
                    FoodServiceModel foodServiceModel = this.modelMapper.map(
                            food, FoodServiceModel.class);
                    return foodServiceModel;
                }).orElse(null);
    }

    @Override
    public void deleteById(String id) {
        if (this.foodRepository.findById(id).isPresent()) {
            this.foodRepository.deleteById(id);
        }
    }

    @Override
    public void addFood(FoodServiceModel foodServiceModel) {
        if(this.foodRepository.findByName(foodServiceModel.getName()) == null){
           Food food = this.modelMapper.map(foodServiceModel, Food.class);
           food.setName(foodServiceModel.getName().toLowerCase());
           this.foodRepository.saveAndFlush(food);
        }
    }

    @Override
    public void editFood(FoodServiceModel foodServiceModel) {
        Optional foodOptional = this.foodRepository.findById(foodServiceModel.getId());
        if(foodOptional.isPresent()) {
            Food food = (Food)foodOptional.get();
            food.setName(foodServiceModel.getName().toLowerCase());
            food.setImage(foodServiceModel.getImage());
            food.setCalories(foodServiceModel.getCalories());
            food.setCarbohydrates(foodServiceModel.getCarbohydrates());
            food.setCategory(FoodCategory.valueOf(foodServiceModel.getCategory()));
            food.setFats(foodServiceModel.getFats());
            food.setSugars(foodServiceModel.getSugars());
            food.setProteins(foodServiceModel.getProteins());
            this.foodRepository.saveAndFlush(food);
        }
    }

    static class Sort implements Comparator<FoodServiceModel> {
        @Override
        public int compare(FoodServiceModel o1, FoodServiceModel o2) {
            return o1.getName().compareTo(o2.getName());
        }
    }
}

