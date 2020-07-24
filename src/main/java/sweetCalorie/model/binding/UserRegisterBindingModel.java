package sweetCalorie.model.binding;

import org.hibernate.validator.constraints.Length;
import sweetCalorie.constant.GlobalConstants;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class UserRegisterBindingModel {

    private String username;
    private String email;
    private String password;
    private String confirmPassword;

    public UserRegisterBindingModel() {
    }

    @NotNull(message = GlobalConstants.USERNAME_NEEDED)
    @Length(min = 5, max = 20, message = GlobalConstants.USERNAME_LENGTH)
    @Pattern(regexp = "^[\\p{L}\\d\\w]+$", message = GlobalConstants.USERNAME_SYMBOLS)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @NotNull(message = GlobalConstants.EMAIL_NEEDED)
    @Email(message = GlobalConstants.VALID_EMAIL)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NotNull(message = GlobalConstants.EMPTY_PASSWORD)
    @Length(min = 8, max = 20, message = GlobalConstants.PASSWORD_LENGTH)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
