package sweetCalorie.web;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import sweetCalorie.model.entity.FoodCategory;
import sweetCalorie.model.service.FoodServiceModel;
import sweetCalorie.service.FoodService;
import java.util.List;

@Controller
@RequestMapping("/foods")
public class FoodsController {
    private final FoodService foodService;

    public FoodsController(FoodService foodService) {
        this.foodService = foodService;
    }

    @GetMapping()
    public ModelAndView foods(ModelAndView modelAndView) {
        List<FoodServiceModel> foods = this.foodService.findAllFoods();
        modelAndView.addObject(foods);
        modelAndView.setViewName("allFoods");
        return modelAndView;
    }

    private ModelAndView getCategory(ModelAndView modelAndView, String category){
        List<FoodServiceModel> foods = this.foodService.findAllFoodsByCategory(FoodCategory.valueOf(category));
        modelAndView.addObject(foods);
        modelAndView.setViewName("allFoods");
        return modelAndView;
    }
    @GetMapping("/drinks")
    public ModelAndView drinks(ModelAndView modelAndView) {
        return getCategory(modelAndView,"НАПИТКИ");
    }

    @GetMapping("/fruits")
    public ModelAndView fruits(ModelAndView modelAndView) {
        return getCategory(modelAndView,"ПЛОДОВЕ");
    }

    @GetMapping("/vegetables")
    public ModelAndView vegetables(ModelAndView modelAndView) {
        return getCategory(modelAndView,"ЗЕЛЕНЧУЦИ");
    }

    @GetMapping("/sweets")
    public ModelAndView sweets(ModelAndView modelAndView) {
        return getCategory(modelAndView,"СЛАДКИ");
    }

    @GetMapping("/pasta")
    public ModelAndView pasta(ModelAndView modelAndView) {
        return getCategory(modelAndView,"ТЕСТЕНИ");
    }

    @GetMapping("/dairy")
    public ModelAndView diary(ModelAndView modelAndView) {
        return getCategory(modelAndView,"МЛЕЧНИ");
    }

    @GetMapping("/meat")
    public ModelAndView meat(ModelAndView modelAndView) {
        return getCategory(modelAndView,"ЖИВОТИНСКИ");
    }

    @GetMapping("/fishes")
    public ModelAndView fishes(ModelAndView modelAndView) {
        return getCategory(modelAndView,"РИБА");
    }

    @GetMapping("/details/")
    public ModelAndView details(@RequestParam String id, ModelAndView modelAndView) {
        modelAndView.addObject("food", this.foodService.findById(id));
        modelAndView.setViewName("food");
        return modelAndView;
    }

    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    @GetMapping("/edit/{id}")
    public String delete(@PathVariable("id") String id) {
        this.foodService.deleteById(id);
        return "redirect:/foods";
    }
}