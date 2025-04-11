package br.com.fiap.investech.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Descricao")
    @Size(min = 5, max = 255, message = "deve ter pelo menos 5 caracteres")
    private String description;

    @Positive(message = "Saldo Positivo")
    private BigDecimal amount;

    @PastOrPresent(message = "Data Atual")
    private LocalDate date;

    @NotNull(message = "Campo Obrigatorio")
    @ManyToOne
    private Category category;

    private String type;

}
