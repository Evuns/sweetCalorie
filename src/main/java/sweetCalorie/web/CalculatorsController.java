package sweetCalorie.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sweetCalorie.model.binding.CalculatorBinding;
import sweetCalorie.model.service.BMICalculatorServiceModel;
import sweetCalorie.model.service.UserEmailServiceModel;

import javax.validation.Valid;

@Controller
@RequestMapping("/calculators")
public class CalculatorsController {

    public CalculatorsController() {
    }

    @GetMapping("/BMI")
    public String calculatorBMI(Model model) {
        if (!model.containsAttribute("bmiCalculatorServiceModel")) {
            model.addAttribute("bmiCalculatorServiceModel", new BMICalculatorServiceModel());
        }
        return "BMI";
    }
    @GetMapping("/BMI/calculate")
    public String resultsBMI(Model model) {
        if (!model.containsAttribute("bmiCalculatorServiceModel")) {
            model.addAttribute("bmiCalculatorServiceModel", new BMICalculatorServiceModel());
        }
        if (!model.containsAttribute("calculatorBinding")) {
            model.addAttribute("calculatorBinding", new CalculatorBinding());
        }
        return "BMIresult";
    }

    @PostMapping("/BMI")
    public String calculateBMI(@Valid @ModelAttribute("bmiCalculatorServiceModel")
                                       BMICalculatorServiceModel bmiCalculatorServiceModel,
                               BindingResult bindingResult, RedirectAttributes redirectAttributes,
                               @ModelAttribute("calculatorBinding") CalculatorBinding calculatorBinding) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("bmiCalculatorServiceModel", bmiCalculatorServiceModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.bmiCalculatorServiceModel", bindingResult);
            return "BMI";
        }

        String [] details = bmiCalculatorServiceModel.getRange();

        calculatorBinding.setCalculatorDescription(bmiCalculatorServiceModel.getDescription());
        calculatorBinding.setResult(bmiCalculatorServiceModel.calculate());
        calculatorBinding.setWeightCategory(details[0]);
        calculatorBinding.setMessage(details[1]);
        calculatorBinding.setBoundariesMessage(details[2]);

        return "BMIresult";
    }


    //        this.userService.registerUser(this.modelMapper
    //                .map(userRegisterBindingModel, UserServiceModel.class));
    //        return "redirect:login";
    //    }

}
