package ru.study.recipes.messaging;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import ru.study.recipes.messaging.model.AuditMessage;

@Service
@RequiredArgsConstructor
public class MailingService {

    private static final String SUBJECT_TEMPLATE = "Изменение в таблице %s";

    private static final String MESSAGE_TEMPLATE = "Изменение в таблице:\n %s в %s";

    private final JavaMailSender emailSender;

    public void sendSimpleEmail(String toAddress, AuditMessage auditMessage) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(toAddress);
        simpleMailMessage.setSubject(SUBJECT_TEMPLATE.formatted(auditMessage.getTable()));
        simpleMailMessage.setText(MESSAGE_TEMPLATE.formatted(auditMessage.getInfo(), auditMessage.getDatetime()));
        emailSender.send(simpleMailMessage);
    }

}
