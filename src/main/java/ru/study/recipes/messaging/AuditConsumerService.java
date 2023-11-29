package ru.study.recipes.messaging;

import jakarta.persistence.Table;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;
import ru.study.recipes.data.audit.AuditEntity;
import ru.study.recipes.data.audit.AuditRepository;
import ru.study.recipes.data.ingredient.IngredientEntity;
import ru.study.recipes.data.mail.MailingRulesRepository;
import ru.study.recipes.messaging.model.AuditMessage;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuditConsumerService {

    private final AuditRepository auditRepository;

    private final MailingRulesRepository mailingRulesRepository;

    private final MailingService mailingService;

    @Transactional
    @JmsListener(destination = "${application.queue.audit}")
    public void receive(AuditMessage auditMessage) {
        save(auditMessage);
        sendEmails(auditMessage);
    }

    private void save(AuditMessage auditMessage) {
        AuditEntity audit = new AuditEntity();
        audit.setId(UUID.randomUUID());
        audit.setEvent(auditMessage.getEvent());
        audit.setTable(auditMessage.getTable());
        audit.setDatetime(auditMessage.getDatetime());
        audit.setInfo(auditMessage.getInfo());

        auditRepository.persist(audit);
    }

    private void sendEmails(AuditMessage auditMessage) {
        List<String> emails = mailingRulesRepository.findEmailByTableName(auditMessage.getTable());

        emails.forEach(email -> mailingService.sendSimpleEmail(email, auditMessage));
    }

}
