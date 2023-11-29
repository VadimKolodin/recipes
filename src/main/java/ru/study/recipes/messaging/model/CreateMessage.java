package ru.study.recipes.messaging.model;

import lombok.Data;

@Data
public class CreateMessage extends AuditMessage {

    private Object createdObject;

    @Override
    public String getInfo() {
        return "Был создан объект %s".formatted(createdObject);
    }

}
