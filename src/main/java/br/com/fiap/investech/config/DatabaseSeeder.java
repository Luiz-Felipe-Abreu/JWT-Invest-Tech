package br.com.fiap.investech.config;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import br.com.fiap.investech.model.Category;
import br.com.fiap.investech.model.Transaction;
import br.com.fiap.investech.model.User;
import br.com.fiap.investech.model.UserRole;
import br.com.fiap.investech.repository.CategoryRepository;
import br.com.fiap.investech.repository.TransactionRepository;
import br.com.fiap.investech.repository.UserRepository;
import jakarta.annotation.PostConstruct;

@Component
public class DatabaseSeeder {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired 
    private TransactionRepository transactionRepository;

    DatabaseSeeder(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {
        var categories = List.of(
            Category.builder().name("Fundo Imobiliario").icon("Book").build(),
            Category.builder().name("Criptomoeda").icon("Dices").build(),
            Category.builder().name("Renda Fixa").icon("Bus").build(),
            Category.builder().name("Tesouro Direto").icon("House").build()
        );

        categoryRepository.saveAll(categories);

        var descriptions = List.of("Uber para faculdade", "Remédio", "Café especial", "Livro didático", "Cinema", "Bilhete Único", "Restaurante", "Faculdade", "Plano de Saúde", "Aluguel", "Conta de Água", "Conta de Luz", "Streaming");
        var transactions = new ArrayList<Transaction>();

        for(int i= 0; i<50; i++ ){
            transactions.add(Transaction.builder()
                .description(descriptions.get(new Random().nextInt(descriptions.size())))
                .amount(BigDecimal.valueOf(10 + new Random().nextDouble()*500))
                .date(LocalDate.now().minusDays(new Random().nextInt(30)))
                .type("out")
                .category(categories.get(new Random().nextInt(categories.size())))
                .build()
            );
        }

        transactionRepository.saveAll(transactions);

         userRepository.saveAll(List.of(
            User.builder()
                .email("luiz@gmail.com")
                .password(passwordEncoder.encode("12345"))
                .role(UserRole.ADMIN)
                .build(),
            User.builder()
                .email("pedro@gmail.com")
                .password(passwordEncoder.encode("12345"))
                .role(UserRole.USER)
                .build()
        ));

    }

}
