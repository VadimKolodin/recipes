package ru.study.recipes.messaging.consumers;

import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;
import ru.study.recipes.data.mail.MailingRulesRepository;
import ru.study.recipes.messaging.MailingService;
import ru.study.recipes.messaging.model.AuditMessage;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuditMailSender {

    private final MailingRulesRepository mailingRulesRepository;

    private final MailingService mailingService;

    @JmsListener(destination = "${application.topic.audit}")
    public void receive(AuditMessage auditMessage) {
        List<String> emails = mailingRulesRepository.findEmailByTableName(auditMessage.getTable());

        emails.forEach(email -> mailingService.sendSimpleEmail(email, auditMessage));
    }

}
