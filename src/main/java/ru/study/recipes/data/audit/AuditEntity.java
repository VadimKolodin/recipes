package ru.study.recipes.data.audit;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.study.recipes.messaging.model.AuditEvent;

import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "audit")
public class AuditEntity {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "event")
    @Enumerated(value = EnumType.STRING)
    private AuditEvent event;

    @Column(name = "table_name")
    private String table;

    @Column(name = "datetime")
    private LocalDateTime datetime;

    @Column(name = "info")
    private String info;

}
