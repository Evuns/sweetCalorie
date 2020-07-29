package sweetCalorie.web;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sweetCalorie.model.binding.UserLoginBindingModel;
import sweetCalorie.model.binding.UserRegisterBindingModel;
import sweetCalorie.model.service.UserServiceModel;
import sweetCalorie.service.UserService;
import sweetCalorie.validation.UserRegisterValidator;
import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/users")
public class UsersRegisterController {

    private final UserService userService;
    private final ModelMapper modelMapper;
    private final UserRegisterValidator userRegisterValidator;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UsersRegisterController(UserService userService, ModelMapper modelMapper,
                                   BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.userRegisterValidator = new UserRegisterValidator(this.userService);
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PreAuthorize("isAnonymous()")
    @GetMapping("/register")
    public String register(Model model) {
        if (!model.containsAttribute("userRegisterBindingModel")) {
            model.addAttribute("userRegisterBindingModel", new UserRegisterBindingModel());
        }
        return "register";
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

        this.userService.register(this.modelMapper
                .map(userRegisterBindingModel, UserServiceModel.class));
        return "redirect:login";
    }

    @PreAuthorize("isAnonymous()")
    @GetMapping("/login")
    public String login(Model model) {
        if (!model.containsAttribute("userLoginBindingModel")) {
            model.addAttribute("userLoginBindingModel", new UserLoginBindingModel());
        }
        return "login";
    }

    @PostMapping("/login")
    public ModelAndView loginConfirm(@Valid @ModelAttribute
            ("userLoginBindingModel") UserLoginBindingModel userLoginBindingModel,
                                     @AuthenticationPrincipal Principal principal,
                                     BindingResult bindingResult,
                                     RedirectAttributes redirectAttributes,
                                     ModelAndView modelAndView) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userLoginBindingModel", bindingResult);
            modelAndView.setViewName("redirect:login?error=true");
        }

        UserServiceModel user = this.userService.findByUsername(userLoginBindingModel.getUsername());
        if (user == null || !bCryptPasswordEncoder.matches(userLoginBindingModel.getPassword(), user.getPassword())) {
            redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel);
            redirectAttributes.addFlashAttribute("notFound", true);
            modelAndView.setViewName("redirect:login?error=true");
        } else {
            modelAndView.addObject("user", principal);
            modelAndView.setViewName("redirect:/");
        }
        return modelAndView;
    }
}