package br.com.fiap.investech.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.investech.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{

    List<Transaction> findByDescriptionContainingIgnoringCase(String description);

    List<Transaction> findByDescriptionContainingIgnoringCaseAndDate(String description, LocalDate date);

    List<Transaction> findByDate(LocalDate date);
    
}
