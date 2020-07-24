package sweetCalorie.model.binding;

import org.hibernate.validator.constraints.Length;
import sweetCalorie.constant.GlobalConstants;

public class UserLoginBindingModel {
    private String username;
    private String password;

    public UserLoginBindingModel() {
    }

    @Length(min = 5, max = 20, message = GlobalConstants.USERNAME_LENGTH)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Length(min = 8, max = 20, message = GlobalConstants.PASSWORD_LENGTH)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
