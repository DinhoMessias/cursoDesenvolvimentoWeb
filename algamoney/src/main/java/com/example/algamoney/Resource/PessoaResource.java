package com.example.algamoney.Resource;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.algamoney.Event.CadastrarEvent;
import com.example.algamoney.Model.Endereco;
import com.example.algamoney.Model.Pessoa;
import com.example.algamoney.Repository.PessoaRepository;
import com.example.algamoney.Service.PessoaService;

@RestController
@RequestMapping("/pessoa")
public class PessoaResource {

	@Autowired
	private PessoaRepository pessoaRepository;

	@Autowired
	private PessoaService pessoaService;

	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping
	public List<Pessoa> listar() {
		return pessoaRepository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Optional<Pessoa>> buscarPorId(@PathVariable @Valid Long id) {
		return pessoaService.buscarById(id);
	}

	@PostMapping
	public ResponseEntity<Pessoa> adicionarPessoa(@RequestBody @Valid Pessoa p1, HttpServletResponse response) {
		Pessoa pessoa = pessoaRepository.save(p1);
		publisher.publishEvent(new CadastrarEvent(this, response, pessoa.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(pessoa);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void removerPessoa(@PathVariable Long id) {
		pessoaRepository.deleteById(id);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Pessoa> editarPessoa(@PathVariable Long id, @RequestBody @Valid Pessoa p1) {
		Pessoa pessoa = pessoaService.editarPessoa(id, p1);
		return ResponseEntity.ok(pessoa);
	}

	@PutMapping("/{id}/nome")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void editarNomePessoa(@PathVariable Long id, @RequestBody @Valid String nome) {
		pessoaService.editarNomePessoa(id, nome);
	}

	@PutMapping("/{id}/endereco")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void editarEndereco(@PathVariable Long id, @RequestBody @Valid Endereco endereco) {
		pessoaService.editarEndereco(id, endereco);
	}

	@PutMapping("/{id}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void editarAtivo(@PathVariable Long id, @RequestBody @Valid Boolean ativo) {
		pessoaService.editarAtivo(id, ativo);
	}

}
