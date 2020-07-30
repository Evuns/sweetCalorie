package sweetCalorie.model.service;

import sweetCalorie.constant.GlobalConstants;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.text.DecimalFormat;

public abstract class BaseCalculatorServiceModel {

    private double weight;
    private int height;
    private DecimalFormat decimalFormat;

    protected BaseCalculatorServiceModel() {
        this.decimalFormat = new DecimalFormat("##.##");
    }

    @NotNull(message = GlobalConstants.MESSAGE_NEEDED)
    @DecimalMin(value = "0", message = "Височината" + GlobalConstants.POSITIVE_NUMBER)
    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @NotNull(message = GlobalConstants.MESSAGE_NEEDED)
    @Min(value = 0, message = "Теглото " +  GlobalConstants.POSITIVE_NUMBER)
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public DecimalFormat getDecimalFormat() {
        return decimalFormat;
    }

    public void setDecimalFormat(DecimalFormat decimalFormat) {
        this.decimalFormat = decimalFormat;
    }
}
