package sweetCalorie.web;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

    public IndexController() {
    }

    @GetMapping("/")
    public ModelAndView index(ModelAndView modelAndView) {
        if(SecurityContextHolder.getContext().getAuthentication() == null) {
            modelAndView.setViewName("index");
        } else{
            modelAndView.setViewName("home");
        }
        return modelAndView;
    }
}

