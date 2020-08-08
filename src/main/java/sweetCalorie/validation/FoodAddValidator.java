package sweetCalorie.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import sweetCalorie.constant.GlobalConstants;
import sweetCalorie.model.binding.FoodAddBindingModel;
import sweetCalorie.model.entity.FoodCategory;
import sweetCalorie.model.service.FoodServiceModel;
import sweetCalorie.service.FoodService;

@Component
public class FoodAddValidator implements org.springframework.validation.Validator {

    private final FoodService foodService;

    @Autowired
    public FoodAddValidator(FoodService foodService) {
        this.foodService = foodService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return FoodServiceModel.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        FoodAddBindingModel foodAddBindingModel = (FoodAddBindingModel) o;
        if (this.foodService.findByName(foodAddBindingModel.getName()) != null) {
            errors.rejectValue(
                    "name",
                    GlobalConstants.FOOD_IN_DB,
                    GlobalConstants.FOOD_IN_DB);
        }
        if(foodAddBindingModel.getCategory().equals("null")){
            errors.rejectValue(
                    "category",
                    GlobalConstants.FIELD_NEEDED,
                    GlobalConstants.FIELD_NEEDED);
        }
    }
}

