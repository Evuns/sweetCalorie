package sweetCalorie.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AboutUsController {

    public AboutUsController() {
    }

    @GetMapping("/about")
    public ModelAndView aboutUs(ModelAndView modelAndView){
        modelAndView.setViewName("about");
        return modelAndView;
    }

    @GetMapping("/sitemap")
    public ModelAndView siteMap(ModelAndView modelAndView){
        modelAndView.setViewName("sitemap");
        return modelAndView;
    }
}
