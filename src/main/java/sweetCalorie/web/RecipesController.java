package sweetCalorie.web;

import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sweetCalorie.model.binding.RecipeAddBindingModel;
import sweetCalorie.model.service.RecipeServiceModel;
import sweetCalorie.service.RecipeService;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/recipes")
public class RecipesController {
    private final RecipeService recipeService;
    private final ModelMapper modelMapper;

    public RecipesController(RecipeService recipeService, ModelMapper modelMapper) {
        this.recipeService = recipeService;
        this.modelMapper = modelMapper;
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

    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    @GetMapping("/add")
    public ModelAndView addRecipe(ModelAndView modelAndView) {
       modelAndView.addObject("recipeAddBindingModel", new
               RecipeAddBindingModel());
        modelAndView.setViewName("addRecipe");
        return modelAndView;
    }

    @PostMapping("/add")
    public String successfullyAdd(@Valid @ModelAttribute("recipeAddBindingModel")
                                          RecipeAddBindingModel recipeAddBindingModel,
                                  BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("recipeAddBindingModel", recipeAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.recipeAddBindingModel", bindingResult);
            return "redirect:add";
        }

        this.recipeService.addRecipe(this.modelMapper
                .map(recipeAddBindingModel, RecipeServiceModel.class));
        return "redirect:allRecipes";
    }

    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") String id) {
        this.recipeService.deleteById(id);
        return "redirect:/recipes";
    }
}
