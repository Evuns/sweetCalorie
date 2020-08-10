package sweetCalorie.model.service;

import org.hibernate.validator.constraints.Length;
import sweetCalorie.constant.GlobalConstants;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UserEmailServiceModel {

    private String username;
    private String email;
    private String message;
    private boolean sendEmailToME;

    public UserEmailServiceModel() {
    }

    @NotEmpty(message = GlobalConstants.USERNAME_NEEDED)
    @Length(min = 2, max = 20, message = GlobalConstants.FIELD_LENGTH)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Email(message = GlobalConstants.VALID_EMAIL)
    @NotEmpty(message = GlobalConstants.EMAIL_NEEDED)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NotEmpty(message = GlobalConstants.FIELD_NEEDED)
    @Length(min = 10, message = GlobalConstants.EMAIL_LENGTH)
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSendEmailToME() {
        return sendEmailToME;
    }

    public void setSendEmailToME(boolean sendEmailToME) {
        this.sendEmailToME = sendEmailToME;
    }
}
