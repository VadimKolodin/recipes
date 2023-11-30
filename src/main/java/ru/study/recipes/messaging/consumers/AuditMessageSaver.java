package ru.study.recipes.messaging.consumers;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;
import ru.study.recipes.data.audit.AuditEntity;
import ru.study.recipes.data.audit.AuditRepository;
import ru.study.recipes.messaging.model.AuditMessage;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuditMessageSaver {

    private final AuditRepository auditRepository;

    @Transactional
    @JmsListener(destination = "${application.topic.audit}")
    public void receive(AuditMessage auditMessage) {
       AuditEntity audit = new AuditEntity();
       audit.setId(UUID.randomUUID());
       audit.setEvent(auditMessage.getEvent());
       audit.setTable(auditMessage.getTable());
       audit.setDatetime(auditMessage.getDatetime());
       audit.setInfo(auditMessage.getInfo());

       auditRepository.persist(audit);
    }

}
