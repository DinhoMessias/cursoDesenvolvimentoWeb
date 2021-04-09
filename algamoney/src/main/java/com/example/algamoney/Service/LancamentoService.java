package com.example.algamoney.Service;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.algamoney.Model.Lancamento;
import com.example.algamoney.Repository.LancamentoRepository;

@Service
public class LancamentoService {

	@Autowired
	private LancamentoRepository lancamentoRepository;

	public ResponseEntity<Optional<Lancamento>> buscarById(@PathVariable @Valid Long id) {
		Optional<Lancamento> lancamento = lancamentoRepository.findById(id);
		if (!lancamento.isEmpty()) {
			return ResponseEntity.ok(lancamento);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}
