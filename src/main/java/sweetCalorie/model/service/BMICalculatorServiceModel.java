package sweetCalorie.model.service;

import sweetCalorie.constant.GlobalConstants;


public class BMICalculatorServiceModel extends BaseCalculatorServiceModel {
private static final int WEIGHT_UNDER = 19;
private static final int WEIGHT_IDEAL = 25;
private static final int WEIGHT_OVER = 30;
private static final int WEIGHT_OBESITY = 35;
private static final int WEIGHT_MORBID = 40;

    private double BMI;


    public BMICalculatorServiceModel() {
        super.setDescription(GlobalConstants.BMI_DESCRIPTION);
    }

    private double getBMI() {
        return BMI;
    }

    private void setBMI() {
        this.BMI = super.getWeight() / Math.pow(super.getHeight(), 2);
    }

    public String calculate() {
        return super.getDecimalFormat().format(BMI);
    }

    public String[] getRange() {
        String [] stateMessage = new String[3];
        String weightCategory;
        String message;
        if (BMI < WEIGHT_UNDER) {
            weightCategory = "Подноремено тегло";
            message = GlobalConstants.WEIGHT_UNDER;
        } else if (BMI < WEIGHT_IDEAL) {
            weightCategory = "Нормално (Здравословно) тегло";
            message = GlobalConstants.WEIGHT_IDEAL;
        } else if (BMI < WEIGHT_OVER) {
            weightCategory = "Наднормено тегло";
            message = GlobalConstants.WEIGHT_OVER;
        } else if (BMI < WEIGHT_OBESITY) {
            weightCategory = "Умерено затлъстяване";
            message = GlobalConstants.WEIGHT_OBESE;
        } else if (BMI < WEIGHT_MORBID) {
            weightCategory = "Сериозно затлъстяване";
            message = GlobalConstants.WEIGHT_OBESE;
        } else {
            weightCategory = "Болестно състочние";
            message = GlobalConstants.WEIGHT_OBESE;
        }
        String minHealthyWeight = super.getDecimalFormat().format(WEIGHT_UNDER * Math.pow(super.getHeight(), 2));
        String maxHealthyWeight = super.getDecimalFormat().format(WEIGHT_IDEAL * Math.pow(super.getHeight(), 2));

        stateMessage[0] = weightCategory;
        stateMessage[1] = message;
        stateMessage[2] = String.format(GlobalConstants.WEIGHT_BOUNDARIES +
                "%d см, за да сте в нормите трябва да тежите между %s и %s.",
                super.getHeight(), minHealthyWeight, maxHealthyWeight);
        return stateMessage;
    }
}
