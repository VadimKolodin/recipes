package ru.study.recipes.data.mail;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "mailing_rules")
public class MailingRuleEntity {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "table_name")
    private String tableName;

    @Column(name = "email")
    private String email;

}
