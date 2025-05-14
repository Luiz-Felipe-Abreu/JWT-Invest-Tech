package br.com.fiap.investech.specification;

import br.com.fiap.investech.model.Transaction;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TransactionSpecification {

    public static Specification<Transaction> hasType(String type) {
        return (root, query, cb) ->
                type == null ? null : cb.equal(root.get("type"), type);
    }

    public static Specification<Transaction> hasDate(LocalDate date) {
        return (root, query, cb) ->
                date == null ? null : cb.equal(root.get("date"), date);
    }

    public static Specification<Transaction> hasValueGreaterThan(BigDecimal minValue) {
        return (root, query, cb) ->
                minValue == null ? null : cb.greaterThanOrEqualTo(root.get("value"), minValue);
    }

    public static Specification<Transaction> hasValueLessThan(BigDecimal maxValue) {
        return (root, query, cb) ->
                maxValue == null ? null : cb.lessThanOrEqualTo(root.get("value"), maxValue);
    }

    public static Specification<Transaction> descriptionContains(String description) {
        return (root, query, cb) ->
                description == null ? null :
                cb.like(cb.lower(root.get("description")), "%" + description.toLowerCase() + "%");
    }

    public static Specification<Transaction> dateIs(LocalDate date) {
        return (root, query, cb) ->
                date == null ? null : cb.equal(root.get("date"), date);
    }
}
