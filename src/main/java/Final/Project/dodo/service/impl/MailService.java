package Final.Project.dodo.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;


@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    public SimpleMailMessage send(String to, String text) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        LocalDateTime now = LocalDateTime.now();

        Date date = Date.from(now.atZone(ZoneId.systemDefault()).toInstant());

        simpleMailMessage.setFrom(from);

        simpleMailMessage.setTo(to);
        simpleMailMessage.setSentDate(date);
        simpleMailMessage.setText(text);

        mailSender.send(simpleMailMessage);

        return simpleMailMessage;
    }
}
