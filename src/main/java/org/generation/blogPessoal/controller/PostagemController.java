package org.generation.blogPessoal.controller;

import java.util.List;

import org.generation.blogPessoal.model.Postagem;
import org.generation.blogPessoal.repository.PostagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/postagens")
@CrossOrigin("*")
public class PostagemController {
	
	//Injeção das dependências para ações com o banco de dados
	@Autowired
	private PostagemRepository repository;

	/*
	 * Busca todas as postagens armazenadas no banco de dados
	 * Exemplo da url: localhost:8080/postagens
	*/
	@GetMapping
	public ResponseEntity<List<Postagem>> getAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	/*
	 * Busca valores do banco de dados correspondente ao ID da postagem
	 * Exemplo da url: localhost:8080/postagens/1
	*/
	@GetMapping("/{id}")
	public ResponseEntity<Postagem> getById(@PathVariable long id){
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}
	
	/*
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
	}
	
}
