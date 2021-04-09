package com.example.algamoney.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.algamoney.Model.Lancamento;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long>{

}
