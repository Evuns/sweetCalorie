package sweetCalorie.web;

import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sweetCalorie.model.binding.FoodAddBindingModel;
import sweetCalorie.model.entity.FoodCategory;
import sweetCalorie.model.service.FoodServiceModel;
import sweetCalorie.service.FoodService;
import sweetCalorie.validation.FoodAddValidator;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/foods")
public class FoodsController {
    private final FoodService foodService;
    private final ModelMapper modelMapper;
    private final FoodAddValidator foodAddValidator;

    public FoodsController(FoodService foodService, ModelMapper modelMapper,
                           FoodAddValidator foodAddValidator) {
        this.foodService = foodService;
        this.modelMapper = modelMapper;
        this.foodAddValidator = foodAddValidator;
    }

    @GetMapping()
    public ModelAndView foods(ModelAndView modelAndView) {
        List<FoodServiceModel> foods = this.foodService.findAllFoods();
        modelAndView.addObject(foods);
        modelAndView.setViewName("allFood");
        return modelAndView;
    }

    private ModelAndView getCategory(ModelAndView modelAndView, String category) {
        List<FoodServiceModel> foods = this.foodService.findAllFoodsByCategory(FoodCategory.valueOf(category));
        modelAndView.addObject(foods);
        modelAndView.setViewName("allFood");
        return modelAndView;
    }

    @GetMapping("/drinks")
    public ModelAndView drinks(ModelAndView modelAndView) {
        return getCategory(modelAndView, "НАПИТКИ");
    }

    @GetMapping("/fruits")
    public ModelAndView fruits(ModelAndView modelAndView) {
        return getCategory(modelAndView, "ПЛОДОВЕ");
    }

    @GetMapping("/vegetables")
    public ModelAndView vegetables(ModelAndView modelAndView) {
        return getCategory(modelAndView, "ЗЕЛЕНЧУЦИ");
    }

    @GetMapping("/sweets")
    public ModelAndView sweets(ModelAndView modelAndView) {
        return getCategory(modelAndView, "СЛАДКИ");
    }

    @GetMapping("/pasta")
    public ModelAndView pasta(ModelAndView modelAndView) {
        return getCategory(modelAndView, "ТЕСТЕНИ");
    }

    @GetMapping("/dairy")
    public ModelAndView diary(ModelAndView modelAndView) {
        return getCategory(modelAndView, "МЛЕЧНИ");
    }

    @GetMapping("/meat")
    public ModelAndView meat(ModelAndView modelAndView) {
        return getCategory(modelAndView, "ЖИВОТИНСКИ");
    }

    @GetMapping("/fishes")
    public ModelAndView fishes(ModelAndView modelAndView) {
        return getCategory(modelAndView, "РИБА");
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

    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    @GetMapping("/add")
    public String addFood(Model model) {
        if (!model.containsAttribute("foodAddBindingModel")) {
            model.addAttribute("foodAddBindingModel", new
                    FoodAddBindingModel());
        }
        return "addFood";
    }

    @PostMapping("/add")
    public String successfullyAdd(@Valid @ModelAttribute("foodAddBindingModel")
                                          FoodAddBindingModel foodAddBindingModel,
                                  BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        this.foodAddValidator.validate(foodAddBindingModel,bindingResult);
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("foodAddBindingModel", foodAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.foodAddBindingModel", bindingResult);
            return "redirect:add";
        }

        this.foodService.addFood(this.modelMapper
                .map(foodAddBindingModel, FoodServiceModel.class));
        return "redirect:/foods";
    }
}