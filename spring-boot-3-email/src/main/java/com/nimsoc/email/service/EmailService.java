package com.nimsoc.email.service;


import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class EmailService {

  @Autowired
  private JavaMailSender mailSender;

  @Autowired
  private TemplateEngine templateEngine;

  public void sendSimpleMessage(String to, String subject, String text) {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setFrom("noreply@example.com");
    message.setTo(to);
    message.setSubject(subject);
    message.setText(text);
    mailSender.send(message);
  }


  public void sendWelcomeEmail(String to, String name) {
    Context context = new Context();
    context.setVariable("name", name);

    String processHtml = templateEngine.process("welcome-email", context);

    MimeMessage mimeMessage = mailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

    try {
      helper.setText(processHtml, true); // true indicates HTML
      helper.setTo(to);
      helper.setSubject("Welcome to Our Service!");
      helper.setFrom("noreply@yourdomain.com");
      mailSender.send(mimeMessage);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }


}
