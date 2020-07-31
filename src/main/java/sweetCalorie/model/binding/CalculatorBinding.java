package sweetCalorie.model.binding;

public class CalculatorBinding {
    private String calculatorDescription;
    private String result;
    private String weightCategory;
    private String message;
    private String boundariesMessage;

    public CalculatorBinding() {
    }

    public String getCalculatorDescription() {
        return calculatorDescription;
    }

    public void setCalculatorDescription(String calculatorDescription) {
        this.calculatorDescription = calculatorDescription;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getWeightCategory() {
        return weightCategory;
    }

    public void setWeightCategory(String weightCategory) {
        this.weightCategory = weightCategory;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getBoundariesMessage() {
        return boundariesMessage;
    }

    public void setBoundariesMessage(String boundariesMessage) {
        this.boundariesMessage = boundariesMessage;
    }
}
