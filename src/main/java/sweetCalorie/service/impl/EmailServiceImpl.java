package sweetCalorie.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import sweetCalorie.constant.GlobalConstants;
import sweetCalorie.model.service.UserEmailServiceModel;
import sweetCalorie.service.EmailService;

@Service
public class EmailServiceImpl implements EmailService {

    private JavaMailSenderImpl javaMailSender;

    @Autowired
    public EmailServiceImpl(JavaMailSenderImpl javaMailSender) {
        this.javaMailSender = javaMailSender;
    }


    @Override
    public void sendEmail(UserEmailServiceModel userEmailServiceModel) throws MailException {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo("sweetcalorie.com@gmail.com");
        mailMessage.setFrom(userEmailServiceModel.getEmail());
        if (userEmailServiceModel.isSendEmailToME()) {
            mailMessage.setTo(userEmailServiceModel.getEmail());
            mailMessage.setFrom("sweetcalorie.com@gmail.com");
            mailMessage.setSubject(GlobalConstants.AUTOMATION_EMAIL);
        }
        mailMessage.setSubject(GlobalConstants.EMAIL_REQUEST + userEmailServiceModel.getUsername());
        mailMessage.setText(userEmailServiceModel.getMessage());

        javaMailSender.setHost("smtp.googlemail.com");
        javaMailSender.setPort(25);
        javaMailSender.setUsername("sweetcalorie.com@gmail.com");
        javaMailSender.setPassword("sweetCalorie123");
        javaMailSender.send(mailMessage);
    }
}
