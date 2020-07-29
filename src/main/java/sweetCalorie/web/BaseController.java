package sweetCalorie.web;

import org.springframework.web.servlet.ModelAndView;
import sweetCalorie.service.UserService;

import java.security.Principal;

public abstract class BaseController {

    private final UserService userService;

    public BaseController(UserService userService) {
        this.userService = userService;
    }

    protected ModelAndView view(String view, ModelAndView modelAndView) {
        modelAndView.setViewName(view);

        return modelAndView;
    }

    protected ModelAndView view(String view) {

        return this.view(view, new  ModelAndView());
    }

    protected ModelAndView redirect(String url) {
        return this.view("redirect:" + url);
    }

//    protected UserServiceModel getUsername(Principal principal) {
//        return this.userService.findUserByUserName(principal.getName());
//    }
}
