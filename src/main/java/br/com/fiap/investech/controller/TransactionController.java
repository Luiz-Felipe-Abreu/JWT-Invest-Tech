package br.com.fiap.investech.controller;

import br.com.fiap.investech.model.Transaction;
import br.com.fiap.investech.repository.TransactionRepository;
import br.com.fiap.investech.specification.TransactionSpecification;
import jakarta.validation.constraints.Size;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionRepository repository;

    public TransactionController(TransactionRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Transaction> getTransactions(
            @RequestParam(required = false) @Size(min = 3) String description,
            @RequestParam(required = false) LocalDate date,
            @RequestParam(required = false, defaultValue = "id") String sortBy
    ) {
        Specification<Transaction> spec = Specification.where(null);

        if (description != null && !description.isBlank()) {
            spec = spec.and(TransactionSpecification.descriptionContains(description));
        }

        if (date != null) {
            spec = spec.and(TransactionSpecification.dateIs(date));
        }

        return repository.findAll(spec, Sort.by(sortBy));
    }
}
