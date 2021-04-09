package com.example.algamoney.Service;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.algamoney.Model.Endereco;
import com.example.algamoney.Model.Pessoa;
import com.example.algamoney.Repository.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;

	public Pessoa editarPessoa(Long id, Pessoa p1) {
		Optional<Pessoa> pessoa = buscarPessoaById(id);
		BeanUtils.copyProperties(p1, pessoa.get(), "id");
		pessoaRepository.save(pessoa.get());
		return pessoa.get();
	}

	public ResponseEntity<Optional<Pessoa>> buscarById(@PathVariable @Valid Long id) {
		Optional<Pessoa> pessoa = pessoaRepository.findById(id);
		if (!pessoa.isEmpty()) {
			return ResponseEntity.ok(pessoa);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	public void editarNomePessoa(Long id, @Valid String nome) {
		Optional<Pessoa> pessoa = buscarPessoaById(id);
		pessoa.get().setNome(nome);
		pessoaRepository.save(pessoa.get());
	}

	public void editarEndereco(Long id, @Valid Endereco endereco) {
		Optional<Pessoa> pessoa = buscarPessoaById(id);
		pessoa.get().setEndereco(endereco);
		pessoaRepository.save(pessoa.get());
	}

	public void editarAtivo(Long id, @Valid Boolean ativo) {
		Optional<Pessoa> pessoa = buscarPessoaById(id);
		pessoa.get().setAtivo(ativo);
		;
		pessoaRepository.save(pessoa.get());
	}

	private Optional<Pessoa> buscarPessoaById(Long id) {
		Optional<Pessoa> pessoa = pessoaRepository.findById(id);
		if (pessoa.isEmpty()) {
			throw new EmptyResultDataAccessException(1);
		}
		return pessoa;
	}
}
