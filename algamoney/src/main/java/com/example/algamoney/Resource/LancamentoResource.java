package com.example.algamoney.Resource;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.algamoney.Model.Lancamento;
import com.example.algamoney.Repository.LancamentoRepository;
import com.example.algamoney.Service.LancamentoService;

@RestController
@RequestMapping("/lancamento")
public class LancamentoResource {

	@Autowired
	private LancamentoRepository lancamentoRepository;

	@Autowired
	private LancamentoService lancamentoService;

	@GetMapping
	public List<Lancamento> listar() {
		return lancamentoRepository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Optional<Lancamento>> buscaById(@PathVariable @Valid Long id) {
		return lancamentoService.buscarById(id);
	}
}
