package org.generation.blogPessoal.controller;

import java.util.List;

import javax.validation.Valid;

import org.generation.blogPessoal.model.Postagem;
import org.generation.blogPessoal.service.PostagemService;
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
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PostagemController {

	@Autowired
	private PostagemService postagemService;

	
	@GetMapping("buscar-todos")
	public ResponseEntity<List<Postagem>> getAll() {
		return postagemService.findAll();
	}

	@GetMapping("/buscar-id")
	public ResponseEntity<Postagem> getById(@RequestParam Long id) {
		return postagemService.findById(id);
	}

	@GetMapping("/buscar-titulo")
	public ResponseEntity<List<Postagem>> getByTitulo(@RequestParam String titulo) {
		return postagemService.findByTitulo(titulo);
	}

	@PostMapping("/cadastrar")
	public ResponseEntity<Postagem> postPostagem(@Valid @RequestBody Postagem postagem) {
		return postagemService.savePostagem(postagem);
	}

	@PutMapping("/alterar")
	public ResponseEntity<Postagem> putPostagem(@Valid @RequestBody Postagem postagem) {
		return postagemService.updatePostagem(postagem);
	}

	@DeleteMapping("/deletar")
	public ResponseEntity<Postagem> deletePostagem(@RequestParam Long id) {
		return postagemService.deletePostagem(id);
	}

}
