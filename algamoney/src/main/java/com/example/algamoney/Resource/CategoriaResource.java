package com.example.algamoney.Resource;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.algamoney.Event.CadastrarEvent;
import com.example.algamoney.Model.Categoria;
import com.example.algamoney.Repository.CategoriaRepository;

@RestController
@RequestMapping("/categoria")
public class CategoriaResource {

	@Autowired
	private CategoriaRepository categoriaRepositry;

	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping
	public List<Categoria> listar() {
		return categoriaRepositry.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Optional<Categoria>> buscarPorId(@PathVariable Long id) {
		Optional<Categoria> categoria = categoriaRepositry.findById(id);
		if (!categoria.isEmpty()) {
			return ResponseEntity.ok(categoria);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<Categoria> adicionarCategoria(@RequestBody @Valid Categoria c1,
			HttpServletResponse response) {
		Categoria categoria = categoriaRepositry.save(c1);
		publisher.publishEvent(new CadastrarEvent(this, response, categoria.getId()));

		return ResponseEntity.status(HttpStatus.CREATED).body(categoria);
	}

}
