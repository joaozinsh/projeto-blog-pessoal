package org.generation.blogPessoal.controller;

import java.util.List;

import org.generation.blogPessoal.model.Postagem;
import org.generation.blogPessoal.repository.PostagemRepository;
import org.generation.blogPessoal.service.PostagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.PathVariable;
=======
>>>>>>> develop
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
	private PostagemRepository repository;
	@Autowired
	private PostagemService service;

	/*
	 * Busca todas as postagens armazenadas no banco de dados. Exemplo da url:
	 * localhost:8080/postagens
	 */
	@GetMapping
<<<<<<< HEAD
	public ResponseEntity<List<Postagem>> getAll(){
		return ResponseEntity.ok(repository.findAll());
=======
	public ResponseEntity<List<Postagem>> getAll() {
		List<Postagem> listaDePostagem = repository.findAll();
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
		return repository.findById(id).map(resp -> ResponseEntity.status(200).body(resp))
				.orElse(ResponseEntity.status(404).build());
	}

	/*
	 * Busca valores do banco de dados correspondente ao titulo da postagem. Exemplo
	 * da url: localhost:8080/postagens?titulo=Titulo
	 */
	@GetMapping(params = "titulo")
	public ResponseEntity<List<Postagem>> getByTitulo(@RequestParam String titulo) {
		List<Postagem> listaPorTitulo = repository.findAllByTituloContainingIgnoreCase(titulo);
		if (listaPorTitulo.isEmpty()) {
			return ResponseEntity.status(404).build();
		} else {
			return ResponseEntity.status(200).body(listaPorTitulo);
		}
>>>>>>> develop
	}

	/*
<<<<<<< HEAD
	 * Busca valores do banco de dados correspondente ao ID da postagem
	 * Exemplo da url: localhost:8080/postagens/1
	*/
	@GetMapping("/{id}")
	public ResponseEntity<Postagem> getById(@PathVariable long id){
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
=======
	 * Adiciona valores no banco de dados atraves dos valores obtidos do body
	 */
	@PostMapping
	public ResponseEntity<Postagem> postPostagem(@RequestBody Postagem postagem) {
		return service.checkPostagem(postagem).map(resp -> ResponseEntity.status(201).body(resp))
				.orElse(ResponseEntity.status(400).build());
>>>>>>> develop
	}

	/*
<<<<<<< HEAD
	 * Busca valores do banco de dados correspondente ao titulo da postagem
	 * Exemplo da url: localhost:8080/postagens/titulo/API
	*/
	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<List<Postagem>> getByTitulo(@PathVariable String titulo){
		return ResponseEntity.ok(repository.findAllByTituloContainingIgnoreCase(titulo));
	}	
	
	/*
	 * Adiciona valores no banco de dados atraves dos valores obtidos do body
	*/
	@PostMapping
	public ResponseEntity<Postagem> postPostagem(@RequestBody Postagem postagem){
		return ResponseEntity.status(201).body(repository.save(postagem));
	}
	
	/*
	 * Atualiza valores do banco de dados atraves dos valores obtidos do body
	*/
	@PutMapping
	public ResponseEntity<Postagem> putPostagem(@RequestBody Postagem postagem){
		return ResponseEntity.status(200).body(repository.save(postagem));
	}
	
	/*
	 * Deleta valores do banco de dados correspondente com ID da postagem
	 * Exemplo da url: localhost:8080/postagens/1
	*/
	@DeleteMapping("/{id}")
	public void deletePostagem(@PathVariable long id) {
		repository.deleteById(id);
=======
	 * Atualiza valores do banco de dados atraves dos valores obtidos do body
	 */
	@PutMapping
	public ResponseEntity<Postagem> putPostagem(@RequestBody Postagem postagem) {
		return ResponseEntity.status(200).body(repository.save(postagem));
>>>>>>> develop
	}

	/*
	 * Deleta valores do banco de dados correspondente com ID da postagem. Exemplo
	 * da url: localhost:8080/postagens?id=1
	 */
	@DeleteMapping(params = "id")
	public void deletePostagem(@RequestParam long id) {
		repository.deleteById(id);
	}

}
