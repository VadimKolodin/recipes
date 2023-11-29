package ru.study.recipes.data.mail;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MailingRulesRepository {

    @PersistenceContext
    private EntityManager em;

    public List<String> findEmailByTableName(String tableName) {

        String q = """
                   select rule.email from MailingRuleEntity rule
                   where rule.tableName = :tableName
                   """;
        return em.createQuery(q, String.class).setParameter("tableName", tableName).getResultList();
    }

}
