package app.project.mail;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;

import javax.mail.MessagingException;

import app.project.entities.User;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.MimeMessageHelper;

import org.thymeleaf.context.Context;

@Service
public class Mail {

	 @Autowired
	 private JavaMailSender javamailSender;
	 private final TemplateEngine templateEngine ;
	 
		public Mail(TemplateEngine templateEngine) {
			this.templateEngine=templateEngine;
		}
		
		public void sendVerificationEmail(User user)
				throws MessagingException, UnsupportedEncodingException {

			Context context=new Context();
			context.setVariable("name", user.getLastName());
			context.setVariable("link","http://localhost:8081/login?email="+user.getEmail());
			String body=templateEngine.process("VerifyEmail", context);
			
			String fromAddress = "myeduconnect20@gmail.com";
			String senderName = "MyEduConnect";
	         
			MimeMessage message = javamailSender.createMimeMessage();
			
			MimeMessageHelper helper = new MimeMessageHelper(message,true);
			String toAddress = user.getEmail();
			helper.setFrom(fromAddress, senderName);
			helper.setTo(toAddress);
			helper.setSubject("Email address Verification");

			helper.setText(body, true);

			javamailSender.send(message);
		}
		
		
		public void SendForgotPassword(User user,String Token)
				throws MessagingException, UnsupportedEncodingException {

			Context context=new Context();
			context.setVariable("token", Token);
			context.setVariable("link","http://localhost:8081/ChangerPassword");
			String body=templateEngine.process("ForgotPassword", context);
			
			String fromAddress = "myeduconnect20@gmail.com";
			String senderName = "MyEduConnect";
	         
			MimeMessage message = javamailSender.createMimeMessage();
			
			MimeMessageHelper helper = new MimeMessageHelper(message,true);
			String toAddress = user.getEmail();
			helper.setFrom(fromAddress, senderName);
			helper.setTo(toAddress);
			helper.setSubject("Email Forgot Password");

			helper.setText(body, true);

			javamailSender.send(message);
		}
		
}
