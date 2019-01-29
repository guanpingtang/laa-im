package com.dipsy.laa.mail;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

/**
 * @author tgp
 */
@Service
public class MailService {

    public void sendMail(String to) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(to);
        mailMessage.setSubject("111111");
        mailMessage.setText("222222");
        mailMessage.setFrom("2");
    }
}
