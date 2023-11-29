package ru.study.recipes.messaging;

import jakarta.persistence.Table;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import ru.study.recipes.messaging.model.AuditEvent;
import ru.study.recipes.messaging.model.AuditMessage;
import ru.study.recipes.messaging.model.CreateMessage;
import ru.study.recipes.messaging.model.DeleteMessage;

import java.time.LocalDateTime;
import javax.print.attribute.standard.Destination;

@Service
@RequiredArgsConstructor
public class AuditProducerService implements EventLogger {

    @Value("${application.queue.audit}")
    private String queueName;

    private final JmsTemplate jmsTemplate;

    @Override
    public void log(Object entity, AuditEvent event) {
        AuditMessage auditMessage = null;

        switch (event) {
            case CREATE -> {
                CreateMessage createMessage = new CreateMessage();
                createMessage.setCreatedObject(entity);
                auditMessage = createMessage;
            }
            case DELETE -> {
                DeleteMessage deleteMessage = new DeleteMessage();
                deleteMessage.setDeletedObject(entity);
                auditMessage = deleteMessage;
            }
        }

        auditMessage.setDatetime(LocalDateTime.now());
        auditMessage.setEvent(event);

        auditMessage.setTable(entity.getClass().getAnnotation(Table.class).name());

        jmsTemplate.convertAndSend(queueName, auditMessage);
    }

}
