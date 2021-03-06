package sweetCalorie.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sweetCalorie.model.service.UserEmailServiceModel;
import sweetCalorie.service.EmailService;
import javax.validation.Valid;

@Controller
public class ContactsController {

    private final EmailService emailService;

    @Autowired
    public ContactsController(EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping("/contacts")
    public String contactUs(Model model) {
        if (!model.containsAttribute("userEmailServiceModel")) {
            model.addAttribute("userEmailServiceModel", new UserEmailServiceModel());
        }
        return "emailContact";
    }

    @PostMapping("/contacts")
    public String sendEmail(@Valid @ModelAttribute("userEmailServiceModel")
                                    UserEmailServiceModel userEmailServiceModel,
                            BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userEmailServiceModel", userEmailServiceModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userEmailServiceModel", bindingResult);
            return "redirect:contacts";
        }
        try {
            this.emailService.sendEmail(userEmailServiceModel);
            return "emailSend";
        } catch (MailException e) {
            e.getCause();
            return "error";
        }
    }
}

