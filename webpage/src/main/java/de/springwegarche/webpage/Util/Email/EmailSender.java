package de.springwegarche.webpage.Util.Email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import de.springwegarche.webpage.Models.User;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

public class EmailSender {
    
    @Autowired
    private static JavaMailSender javaMailSender;

	static void sendEmailAsHtml(String recipient, String subject, String text) throws MessagingException, IOException {

        MimeMessage msg = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(msg, true); // true = multipart message
		
        helper.setTo(recipient);

        helper.setSubject(subject);

        // default = text/plain
        //helper.setText("Check attachment for image!");

        // true = text/html
        helper.setText(text, true);

        javaMailSender.send(msg);
    }

    private void sendPasswordResetMail(User user, String passwordResetToken, boolean german) throws MessagingException, IOException{
        String text = "";
        if(german) {

            EmailSender.sendEmailAsHtml(user.getEmail(),"Password Reset", text);
        } else {
            
            EmailSender.sendEmailAsHtml(user.getEmail(),"Password Reset", text);
        }

        
    }
}
