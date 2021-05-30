package org.generation.blogPessoal.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.generation.blogPessoal.model.Postagem;
import org.generation.blogPessoal.model.Tema;
import org.generation.blogPessoal.repository.PostagemRepository;
import org.generation.blogPessoal.repository.TemaRepository;
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
@RequestMapping("/postagens")
@CrossOrigin("*")
public class PostagemController {

	// Injeção das dependências para ações com o banco de dados
	@Autowired
	private PostagemRepository repositoryPostagem;
	@Autowired
	private TemaRepository repositoryTema;

	/*
	 * Busca todas as postagens armazenadas no banco de dados. Exemplo da url:
	 * localhost:8080/postagens
	 */
	@GetMapping
	public ResponseEntity<List<Postagem>> getAll() {
		List<Postagem> listaDePostagem = repositoryPostagem.findAll();
		if (listaDePostagem.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(listaDePostagem);
		}
	}

	/*
	 * Busca valores do banco de dados correspondente ao ID da postagem. Exemplo da
	 * url: localhost:8080/postagens?id=1
	 */
	@GetMapping(params = "id")
	public ResponseEntity<Postagem> getById(@RequestParam long id) {
		return repositoryPostagem.findById(id).map(resp -> ResponseEntity.status(200).body(resp))
				.orElse(ResponseEntity.status(404).build());
	}

	/*
	 * Busca valores do banco de dados correspondente ao titulo da postagem. Exemplo
	 * da url: localhost:8080/postagens?titulo=Titulo
	 */
	@GetMapping(params = "titulo")
	public ResponseEntity<List<Postagem>> getByTitulo(@RequestParam String titulo) {
		List<Postagem> listaPorTitulo = repositoryPostagem.findAllByTituloContainingIgnoreCase(titulo);
		if (listaPorTitulo.isEmpty()) {
			return ResponseEntity.status(404).build();
		} else {
			return ResponseEntity.status(200).body(listaPorTitulo);
		}
	}

	/*
	 * Adiciona valores no banco de dados atraves dos valores obtidos do body
	 */
	@PostMapping
	public ResponseEntity<Postagem> postPostagem(@Valid @RequestBody Postagem postagem) {
		Optional<Tema> temaExiste = repositoryTema.findById(postagem.getTema().getId());
		if (temaExiste.isPresent()) {
			return ResponseEntity.status(201).body(repositoryPostagem.save(postagem));
		} else {
			return ResponseEntity.status(404).build();
		}
		
	}

	/*
	 * Atualiza valores do banco de dados atraves dos valores obtidos do body
	 */
	@PutMapping
	public ResponseEntity<Postagem> putPostagem(@RequestBody Postagem postagem) {
		Optional<Postagem> idPostagemExiste = repositoryPostagem.findById(postagem.getId());
		Optional<Tema> temaExiste = repositoryTema.findById(postagem.getTema().getId());
		if (idPostagemExiste.isPresent() && temaExiste.isPresent()) {
			return ResponseEntity.status(201).body(repositoryPostagem.save(postagem));
		} else {
			return ResponseEntity.status(404).build();
		}
	}

	/*
	 * Deleta valores do banco de dados correspondente com ID da postagem. Exemplo
	 * da url: localhost:8080/postagens?id=1
	 */
	@DeleteMapping(params = "id")
	public ResponseEntity<Object> deletePostagem(@RequestParam long id) {
		if (repositoryPostagem.findById(id).isPresent()) {
			repositoryPostagem.deleteById(id);
		} else {
			return ResponseEntity.status(404).build();
		}
		return null;
	}

}
