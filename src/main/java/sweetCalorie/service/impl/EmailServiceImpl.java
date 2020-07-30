package sweetCalorie.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import sweetCalorie.constant.GlobalConstants;
import sweetCalorie.model.service.UserEmailServiceModel;
import sweetCalorie.service.EmailService;

@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSenderImpl javaMailSender;
    @Value("${spring.mail.host}")
    private String host;
    @Value("${spring.mail.port}")
    private int port;
    @Value("${spring.mail.username}")
    private String username;
    @Value("${spring.mail.password}")
    private String password;

    @Autowired
    public EmailServiceImpl(JavaMailSenderImpl javaMailSender) {
        this.javaMailSender = javaMailSender;
    }


    @Override
    public void sendEmail(UserEmailServiceModel userEmailServiceModel) throws MailException {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(username);
        mailMessage.setFrom(username);
        if (userEmailServiceModel.isSendEmailToME()) {
            mailMessage.setTo(username,userEmailServiceModel.getEmail());
        }
        mailMessage.setSubject(GlobalConstants.EMAIL_REQUEST + userEmailServiceModel.getUsername());
        mailMessage.setText(String.format("Въпрос от емайл адрес: %s %n%nСъдържание: %s%n%n Този емайл е автоматичен. Моля не отговаряйте на него!", userEmailServiceModel.getEmail(),
                userEmailServiceModel.getMessage()));
        javaMailSender.setHost(host);
        javaMailSender.setPort(port);
        javaMailSender.setUsername(username);
        javaMailSender.setPassword(password);
        javaMailSender.send(mailMessage);
    }
}
