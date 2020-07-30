package sweetCalorie.service;

import sweetCalorie.model.service.UserEmailServiceModel;

public interface EmailService {

    void sendEmail(UserEmailServiceModel userEmailServiceModel);
}
