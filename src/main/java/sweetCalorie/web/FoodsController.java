package sweetCalorie.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
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
    public ModelAndView foods (ModelAndView modelAndView, Model model){
        List<FoodServiceModel> foods = this.foodService.findAllFoods();
        model.addAttribute(foods);
        modelAndView.setViewName("allFoods");
        return modelAndView;
    }

    @GetMapping("/details")
    public ModelAndView details(@RequestParam("id") String id, ModelAndView modelAndView){
            modelAndView.addObject("food", this.foodService.findById(id));
            modelAndView.setViewName("food");
        return modelAndView;
    }
}

