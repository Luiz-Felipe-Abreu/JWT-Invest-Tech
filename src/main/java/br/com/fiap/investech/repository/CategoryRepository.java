package br.com.fiap.investech.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.investech.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    
}
