package sweetCalorie.web;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sweetCalorie.model.binding.UserLoginBindingModel;
import sweetCalorie.model.binding.UserRegisterBindingModel;
import sweetCalorie.model.entity.Role;
import sweetCalorie.model.service.UserServiceModel;
import sweetCalorie.model.view.UserAllViewModel;
import sweetCalorie.service.UserService;
import sweetCalorie.validation.UserRegisterValidator;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/users")
public class UsersController {

    private final UserService userService;
    private final ModelMapper modelMapper;
    private final UserRegisterValidator userRegisterValidator;

    @Autowired
    public UsersController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.userRegisterValidator = new UserRegisterValidator(this.userService);
    }

    @PreAuthorize("isAnonymous()")
    @GetMapping("/register")
    public String register(Model model) {
        if (!model.containsAttribute("userRegisterBindingModel")) {
            model.addAttribute("userRegisterBindingModel", new UserRegisterBindingModel());
        }
        return "userRegister";
    }

    @PostMapping("/register")
    public String registerConfirm(@Valid @ModelAttribute("userRegisterBindingModel")
                                          UserRegisterBindingModel userRegisterBindingModel,
                                  BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        this.userRegisterValidator.validate(userRegisterBindingModel, bindingResult);
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult);
            return "redirect:register";
        }

        this.userService.registerUser(this.modelMapper
                .map(userRegisterBindingModel, UserServiceModel.class));
        return "redirect:login";
    }

    @PreAuthorize("isAnonymous()")
    @GetMapping("/login")
    public String login(Model model) {
        if (!model.containsAttribute("userLoginBindingModel")) {
            model.addAttribute("userLoginBindingModel", new UserLoginBindingModel());
        }
        return "userLogin";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/logout")
    public String logout(HttpSession httpSession){
        httpSession.invalidate();
        return "redirect:/users/login";
    }

    @PostMapping("/setUser/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String setUser(@PathVariable String id) {
        this.userService.setUserRole(id, "user");

        return "redirect:/users";
    }

    @PostMapping("/setModerator/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String setModerator(@PathVariable String id) {
        this.userService.setUserRole(id, "moderator");

        return "redirect:/users";
    }

    @GetMapping()
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView getAllUser(ModelAndView modelAndView, Principal principal) {
        List<UserAllViewModel> users = this.userService.findAllUsers()
                .stream()
                .map(u -> {
                    UserAllViewModel user = this.modelMapper.map(u, UserAllViewModel.class);
                    Set<String> authorities = u.getAuthorities().stream()
                            .map(Role::getAuthority).collect(Collectors.toSet());
                    user.setAuthorities(authorities);
                    return user;
                })
                .collect(Collectors.toList());
        modelAndView.addObject("users", users);
        modelAndView.addObject("username", principal.getName());
        modelAndView.setViewName("usersAll");

        return  modelAndView;
    }

    @PostMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteUser(@PathVariable String id) {
        this.userService.deleteUser(id);

        return "redirect:/users";
    }
}