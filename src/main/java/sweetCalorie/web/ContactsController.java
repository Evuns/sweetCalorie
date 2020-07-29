package sweetCalorie.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ContactsController {

    public ContactsController() {
    }

    @GetMapping("/contacts")
    public ModelAndView aboutUs(ModelAndView modelAndView){
        modelAndView.setViewName("contacts");
        return modelAndView;
    }
}
