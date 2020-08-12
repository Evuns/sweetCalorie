package sweetCalorie.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/calculators")
public class CalculatorsController {

    public CalculatorsController() {
    }

    @GetMapping()
    public String calculators(){
        return "calculators";
    }

    @GetMapping("/BMI")
    public String calculatorBMI() {
        return "calculatorBMI";
    }

    @GetMapping("/BMR")
    public String calculatorBMR() {
        return "calculatorBMR";
    }

    @GetMapping("/caloriesCalculator")
    public String caloriesCalculator() {
        return "calculatorCalories";
    }

    @GetMapping("/fatPercentage")
    public String fatPercentageCalc() {
        return "calculatorFatPercentage";
    }

}
