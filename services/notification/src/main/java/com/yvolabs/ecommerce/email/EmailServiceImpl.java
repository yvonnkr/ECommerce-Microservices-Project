package com.yvolabs.ecommerce.email;

import com.yvolabs.ecommerce.kafka.order.Product;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.springframework.mail.javamail.MimeMessageHelper.MULTIPART_MODE_RELATED;


/**
 * @author Yvonne N
 * @version 1.0
 * @since 23/07/2024
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    @Value("${api.props.host-email}")
    private String SENDER_EMAIL;

    @Async
    @Override
    public void sendPaymentSuccessEmail(String destinationEmail, String customerName, BigDecimal amount, String orderReference) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, MULTIPART_MODE_RELATED, UTF_8.name());
        messageHelper.setFrom(SENDER_EMAIL);

        String templateName = EmailTemplates.PAYMENT_CONFIRMATION.getTemplate();
        String templateSubject = EmailTemplates.PAYMENT_CONFIRMATION.getSubject();

        Map<String, Object> variables = new HashMap<>();
        variables.put("customerName", customerName);
        variables.put("amount", amount);
        variables.put("orderReference", orderReference);
        variables.put("dateReserve", LocalDateTime.now().getYear());

        Context thymeleafContext = new Context();
        thymeleafContext.setVariables(variables);

        messageHelper.setSubject(templateSubject);

        try {
            String htmlTemplate = templateEngine.process(templateName, thymeleafContext);
            messageHelper.setText(htmlTemplate, true);
            messageHelper.setTo(destinationEmail);
            mailSender.send(mimeMessage);
            emailSuccessLogMessage(destinationEmail, templateName);
        } catch (MessagingException e) {
            emailErrorLogMessage(destinationEmail, e);
        }

    }

    @Async
    @Override
    public void sendOrderConfirmationEmail(String destinationEmail, String customerName, BigDecimal amount, String orderReference, List<Product> products) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, MULTIPART_MODE_RELATED, UTF_8.name());
        messageHelper.setFrom(SENDER_EMAIL);

        final String templateName = EmailTemplates.ORDER_CONFIRMATION.getTemplate();
        final String templateSubject = EmailTemplates.ORDER_CONFIRMATION.getSubject();

        Map<String, Object> variables = new HashMap<>();
        variables.put("customerName", customerName);
        variables.put("totalAmount", amount);
        variables.put("orderReference", orderReference);
        variables.put("products", products);
        variables.put("dateReserve", LocalDateTime.now().getYear());

        Context thymeleafContext = new Context();
        thymeleafContext.setVariables(variables);

        messageHelper.setSubject(templateSubject);

        try {
            String htmlTemplate = templateEngine.process(templateName, thymeleafContext);
            messageHelper.setText(htmlTemplate, true);
            messageHelper.setTo(destinationEmail);
            mailSender.send(mimeMessage);
            emailSuccessLogMessage(destinationEmail, templateName);
        } catch (MessagingException e) {
            emailErrorLogMessage(destinationEmail, e);
        }
    }

    private static void emailErrorLogMessage(String destinationEmail, MessagingException e) {
        log.warn("WARNING - An error occurred when trying to send email. Cannot send Email to {} ", destinationEmail);
        log.warn("Error Message: {}", e.getMessage());
    }

    private static void emailSuccessLogMessage(String destinationEmail, String templateName) {
        log.info("INFO - Email successfully sent to {} with template {} ", destinationEmail, templateName);
    }
}
