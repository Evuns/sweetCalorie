package sweetCalorie.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import sweetCalorie.model.service.RecipeServiceModel;
import sweetCalorie.service.RecipeService;
import java.util.List;

@Controller
@RequestMapping("/recipes")
public class RecipesController {
    private final RecipeService recipeService;

    public RecipesController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping()
    public ModelAndView recipes(ModelAndView modelAndView, Model model) {
        List<RecipeServiceModel> recipes = this.recipeService.findAllRecipes();
        model.addAttribute(recipes);
        modelAndView.setViewName("allRecipes");
        return modelAndView;
    }

    @GetMapping("/details/")
    public ModelAndView details(@RequestParam String id, ModelAndView modelAndView) {
        RecipeServiceModel recipe = this.recipeService.findById(id);
        modelAndView.addObject("recipe", recipe);
        modelAndView.addObject("ingredients", recipe.getIngredients());
        modelAndView.addObject("comments", recipe.getComments());
        modelAndView.setViewName("recipe");
        return modelAndView;
    }
}
