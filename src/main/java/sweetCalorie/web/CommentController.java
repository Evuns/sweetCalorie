package sweetCalorie.web;

import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sweetCalorie.model.binding.CommentAddBindingModel;
import sweetCalorie.model.entity.User;
import sweetCalorie.model.entity.UserProfile;
import sweetCalorie.model.service.CommentServiceModel;
import sweetCalorie.model.service.RecipeServiceModel;
import sweetCalorie.model.service.UserProfileServiceModel;
import sweetCalorie.service.CommentService;
import sweetCalorie.service.RecipeService;
import sweetCalorie.service.UserProfileService;
import sweetCalorie.service.UserService;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/comments")
public class CommentController {

    private final ModelMapper modelMapper;
    private final UserService userService;
    private final CommentService commentService;
    private final RecipeService recipeService;
    private final UserProfileService userProfileService;

    public CommentController(ModelMapper modelMapper, UserService userService,
                             CommentService commentService, RecipeService recipeService, UserProfileService userProfileService) {
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.commentService = commentService;
        this.recipeService = recipeService;
        this.userProfileService = userProfileService;
    }

    @PostMapping("/commentRecipe/{id}")
    @PreAuthorize("isAuthenticated()")
    public String createComment(@PathVariable String id,
                                @ModelAttribute("commentAddBindingModel") @Valid CommentAddBindingModel commentAddBindingModel,
                                BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model,
                                Principal principal) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("commentAddBindingModel", commentAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.commentAddBindingModel", bindingResult);
            return "redirect:/recipes/details/?id=" + id;
        }
        this.recipeService.addComment(commentAddBindingModel,
                this.recipeService.findById(id),
                principal);

        return "redirect:/recipes/details/?id=" + id;
    }

    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") String id, ModelAndView modelAndView) {
        CommentServiceModel commentServiceModel = commentService.findById(id);
        RecipeServiceModel recipeServiceModel = commentServiceModel.getRecipeServiceModel();
        modelAndView.setViewName("redirect:/recipes/details/?id=" + recipeServiceModel.getId());
        this.recipeService.deleteComment(recipeServiceModel, commentServiceModel);
        this.commentService.deleteComment(id);
        return modelAndView;
    }
}
