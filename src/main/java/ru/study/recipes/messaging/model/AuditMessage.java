package ru.study.recipes.messaging.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public abstract class AuditMessage {

    private AuditEvent event;

    private String table;

    private LocalDateTime datetime;

    public abstract String getInfo();

}
