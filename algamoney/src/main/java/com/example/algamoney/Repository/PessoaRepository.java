package com.example.algamoney.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.algamoney.Model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}
