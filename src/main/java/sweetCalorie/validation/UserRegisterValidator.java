package sweetCalorie.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import sweetCalorie.constant.GlobalConstants;
import sweetCalorie.model.binding.UserRegisterBindingModel;
import sweetCalorie.model.service.UserServiceModel;
import sweetCalorie.service.UserService;

@Component
public class UserRegisterValidator implements org.springframework.validation.Validator {

    private final UserService userService;

    @Autowired
    public UserRegisterValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UserServiceModel.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserRegisterBindingModel userRegisterBindingModel = (UserRegisterBindingModel) o;
        try {
            if (!userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())) {
                errors.rejectValue(
                        "password",
                        GlobalConstants.PASSWORD_EQUALITY,
                        GlobalConstants.PASSWORD_EQUALITY);
            }
        } catch (Exception ignored) {
        }
        if (this.userService.findByEmail(userRegisterBindingModel.getEmail()) != null) {
            errors.rejectValue(
                    "email",
                    GlobalConstants.EMAIL_IN_DB,
                    GlobalConstants.EMAIL_IN_DB);
        }
    }
}