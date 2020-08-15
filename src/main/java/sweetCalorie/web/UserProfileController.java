package sweetCalorie.web;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sweetCalorie.model.service.FoodServiceModel;
import sweetCalorie.model.service.RecipeServiceModel;
import sweetCalorie.model.service.UserServiceModel;
import sweetCalorie.service.FoodService;
import sweetCalorie.service.RecipeService;
import sweetCalorie.service.UserProfileService;
import sweetCalorie.service.UserService;

import java.security.Principal;

@Controller
@RequestMapping("/favourites")
public class UserProfileController {
    private final UserProfileService userProfileService;
    private final UserService userService;
    private final RecipeService recipeService;
    private final FoodService foodService;

    public UserProfileController(UserProfileService userProfileService,
                                 UserService userService,
                                 RecipeService recipeService1, FoodService foodService) {
        this.userProfileService = userProfileService;
        this.userService = userService;
        this.recipeService = recipeService1;
        this.foodService = foodService;
    }


    @PostMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public String addToFavouriteRecipe(@PathVariable String id,
                                       Principal principal) {
        UserServiceModel userServiceModel = this.userService
                .findByUsername(principal.getName());
        RecipeServiceModel recipeServiceModel = this.recipeService.findById(id);
        this.userProfileService.addFavouriteRecipe(userServiceModel, recipeServiceModel);
        return "redirect:/recipes/details/?id=" + id;
    }

    @PostMapping("/food/{id}")
    @PreAuthorize("isAuthenticated()")
    public String addToFavouriteFood(@PathVariable String id,
                                       Principal principal) {
        UserServiceModel userServiceModel = this.userService
                .findByUsername(principal.getName());
        FoodServiceModel foodServiceModel = this.foodService.findById(id);
        this.userProfileService.addFavouriteFood(userServiceModel, foodServiceModel);
        return "redirect:/foods/details/?id=" + id;
    }

//    @GetMapping("/all")
//    @PreAuthorize("isAuthenticated()")
//    public ModelAndView getAll(Principal principal, ModelAndView modelAndView) {
//        var user = getUsername(principal);
//        List<SavedRecipeServiceModel> all = this.savedRecipeService.findAll(user.getId());
//
//        modelAndView.addObject("recipes", all);
//        modelAndView.addObject("username", user.getUsername());
//
//        return super.view("saved/all", modelAndView);
//    }
//
//    @PostMapping("/delete/{id}")
//    @PreAuthorize("isAuthenticated()")
//    public ModelAndView delete(@PathVariable String id) {
//        this.savedRecipeService.delete(id);
//
//        return super.redirect("/saved/all");
//    }
//}
}
