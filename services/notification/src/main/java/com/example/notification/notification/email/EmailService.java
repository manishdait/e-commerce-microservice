package com.example.notification.notification.email;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import com.example.notification.kafka.order.Product;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {
  private final JavaMailSender mailSender;
  private final SpringTemplateEngine templateEngine;

  @Async
  public void sendPaymentSucessEmail(String destinationEmail, String customerName, BigDecimal amount, String orderReference) throws MessagingException {
    MimeMessage mimeMessage = mailSender.createMimeMessage();
    MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

    messageHelper.setFrom("contact@e-commerce.com");

    final String templateName = EmailTemplate.PAYMENT_CONFIRMATION.getTemplate();

    Map<String, Object> variables = new HashMap<>();
    variables.put("customer-name", customerName);
    variables.put("order-reference", orderReference);
    variables.put("amount", amount);

    Context context = new Context();
    context.setVariable("variables", variables);
    messageHelper.setSubject(EmailTemplate.PAYMENT_CONFIRMATION.getSubject());

    try{
      String htmlTemplate = templateEngine.process(templateName, context);
      messageHelper.setText(htmlTemplate, true);
      messageHelper.setTo(destinationEmail);

      mailSender.send(mimeMessage);
      log.info("Payment confirmation mail send to :: {}", customerName);
    } catch (MessagingException e) {
      log.warn("Fail to send payment confirmation email to :: {}", customerName);
    }
  }

  @Async
  public void sendOrderConfirmationEmail(String destinationEmail, String customerName, BigDecimal amount, String orderReference, List<Product> products) throws MessagingException {
    MimeMessage mimeMessage = mailSender.createMimeMessage();
    MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

    messageHelper.setFrom("contact@e-commerce.com");

    final String templateName = EmailTemplate.ORDER_CONFIRMATION.getTemplate();

    Map<String, Object> variables = new HashMap<>();
    variables.put("customer-name", customerName);
    variables.put("order-reference", orderReference);
    variables.put("amount", amount);
    variables.put("products", products);

    Context context = new Context();
    context.setVariable("variables", variables);
    messageHelper.setSubject(EmailTemplate.ORDER_CONFIRMATION.getSubject());

    try{
      String htmlTemplate = templateEngine.process(templateName, context);
      messageHelper.setText(htmlTemplate, true);
      messageHelper.setTo(destinationEmail);

      mailSender.send(mimeMessage);
      log.info("Order confirmation mail send to :: {}", customerName);
    } catch (MessagingException e) {
      log.warn("Fail to send order confirmation email to :: {}", customerName);
    }
  }
}
