package sweetCalorie.web;

import org.springframework.expression.spel.ast.NullLiteral;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import sweetCalorie.model.entity.User;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
public class IndexController {

    public IndexController() {
    }

    @GetMapping("/")
    public ModelAndView index(HttpSession httpSession,
                              ModelAndView modelAndView) {
        if(httpSession.getAttribute("user") == null){
            modelAndView.setViewName("index");
        } else{
            modelAndView.setViewName("home");
        }
        return modelAndView;
    }
}

