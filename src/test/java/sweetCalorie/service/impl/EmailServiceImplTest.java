package sweetCalorie.service.impl;

import org.apache.logging.log4j.message.SimpleMessage;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.mail.Session;
import javax.mail.internet.MimeMessage;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class EmailServiceImplTest {

    private EmailServiceImpl emailServiceImpl;

    private JavaMailSenderImpl javaMailSender;

    private SimpleMailMessage simpleMailMessage;
    private String username;
    private String email;
    private String message;

    @Before
    public void before() {
        simpleMailMessage = new SimpleMailMessage();
        username = "admin";
        email = "admin@abv.bg";
        message = "Testing mail sending message.";
    }


    @Test
    void sendEmail_shouldSendEmailToDB() {
//   SimpleMailMessage mailMessage = new SimpleMailMessage();
//        mailMessage.setTo(username);
//        mailMessage.setFrom(username);
//        if (userEmailServiceModel.isSendEmailToME()) {
//            mailMessage.setTo(username,userEmailServiceModel.getEmail());
//        }
//        mailMessage.setSubject(GlobalConstants.EMAIL_REQUEST + userEmailServiceModel.getUsername());
//        mailMessage.setText(String.format("Въпрос от емайл адрес: %s %n%nСъдържание: %s%n%n Този емайл е автоматичен. Моля не отговаряйте на него!", userEmailServiceModel.getEmail(),
//                userEmailServiceModel.getMessage()));
//        javaMailSender.setHost(host);
//        javaMailSender.setPort(port);
//        javaMailSender.setUsername(username);
//        javaMailSender.setPassword(password);
//        javaMailSender.send(mailMessage);
    }

}