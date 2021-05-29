package org.generation.blogPessoal.controller;

import java.util.List;

import org.generation.blogPessoal.model.Tema;
import org.generation.blogPessoal.repository.TemaRepository;
import org.generation.blogPessoal.service.TemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/temas")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TemaController {

	// Injeção das dependências para ações com o banco de dados
	@Autowired
	private TemaRepository repository;
	@Autowired
	private TemaService service;

	/*
	 * Busca todos os temas armazenados no banco de dados. Exemplo da
	 * url:localhost:8080/temas
	 */
	@GetMapping
	public ResponseEntity<List<Tema>> getAll() {
		List<Tema> listaDeTema = repository.findAll();
		if (listaDeTema.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(listaDeTema);
		}
	}

	/*
	 * Busca valores do banco de dados correspondente com o ID. Exemplo da url:
	 * localhost:8080/temas?id=1
	 */
	@GetMapping(params = "id")
	public ResponseEntity<Tema> getById(@RequestParam long id) {
		return repository.findById(id).map(resp -> ResponseEntity.status(200).body(resp))
				.orElse(ResponseEntity.status(204).build());
	}

	/*
	 * Busca valores do banco de dados correspondente com a descricao. Exemplo da
	 * url: localhost:8080/temas?descricao=1
	 */
	@GetMapping(params = "descricao")
	public ResponseEntity<Tema> getByDescricao(@RequestParam String descricao) {
		return repository.findByDescricaoContainingIgnoreCase(descricao)
				.map(listaPorDescricao -> ResponseEntity.status(200).body(listaPorDescricao))
				.orElse(ResponseEntity.status(204).build());
	}

	/*
	 * Adiciona valores no banco de dados atraves dos valores obtidos do body
	 */
	@PostMapping
	public ResponseEntity<Tema> postTema(@RequestBody Tema tema) {
		return service.cadastrarTema(tema).map(resp -> ResponseEntity.status(201).body(resp))
				.orElse(ResponseEntity.status(400).build());
	}

	/*
	 * Atualiza valores no banco de dados atraves dos valores obtidos do body
	 */
	@PutMapping
	public ResponseEntity<Tema> putTema(@RequestBody Tema tema) {
		return service.cadastrarTema(tema).map(resp -> ResponseEntity.status(200).body(resp))
				.orElse(ResponseEntity.status(400).build());
	}

	/*
	 * Deleta valores do banco de dados correspondente com ID do tema. Exemplo da
	 * url: localhost:8080/temas?id=1
	 */
	@DeleteMapping(params = "id")
	public void deleteTema(@RequestParam long id) {
		repository.deleteById(id);
	}

}
