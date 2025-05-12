package br.com.fiap.investech.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.com.fiap.investech.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long>, JpaSpecificationExecutor<Transaction> {

    List<Transaction> findByDescriptionContainingIgnoreCase(String description);

    List<Transaction> findByDescriptionContainingIgnoreCaseAndDate(String description, LocalDate date);

    List<Transaction> findByDate(LocalDate date);
}
