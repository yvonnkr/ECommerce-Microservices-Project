package com.yvolabs.ecommerce.email;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

/**
 * This config may not be needed if setting the mail props in application.yml
 * In this microservice, notification-service.yml is externalised in the config-server,
 * could not autowire so decided to add this config to resolve error,
 */
//@Configuration
public class MailConfig {

    @Value("${spring.mail.host}")
    private String host;

    @Value("${spring.mail.port}")
    private int port;

    @Value("${spring.mail.username}")
    private String username;

    @Value("${spring.mail.password}")
    private String password;

    @Value("${spring.mail.properties.mail.smtp.trust}")
    private String smtpTrust;

    @Value("${spring.mail.properties.mail.starttls.enabled}")
    private String starttlsEnable;

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host);
        mailSender.setPort(port);
        mailSender.setUsername(username);
        mailSender.setPassword(password);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.trust", smtpTrust);
        props.put("mail.starttls.enabled", starttlsEnable);
        props.put("mail.connectiontimeout", 5000);
        props.put("mail.timeout", 3000);
        props.put("mail.writetimeout", 5000);

        return mailSender;
    }
}


