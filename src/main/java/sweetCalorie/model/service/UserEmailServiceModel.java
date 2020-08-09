package sweetCalorie.model.service;

import sweetCalorie.constant.GlobalConstants;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class UserEmailServiceModel {

    private String username;
    private String email;
    private String message;
    private boolean sendEmailToME;

    public UserEmailServiceModel() {
    }
    @NotNull(message = GlobalConstants.USERNAME_NEEDED)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Email
    @NotNull(message = GlobalConstants.EMAIL_NEEDED)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NotNull(message = GlobalConstants.FIELD_NEEDED)
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
