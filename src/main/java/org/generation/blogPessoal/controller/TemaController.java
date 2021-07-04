package org.generation.blogPessoal.controller;

import java.util.List;

import javax.validation.Valid;

import org.generation.blogPessoal.model.Tema;
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

	@Autowired
	private TemaService temaService;


	@GetMapping("buscar-todos")
	public ResponseEntity<List<Tema>> getAll() {
		return temaService.findAll();
	}

	@GetMapping("/buscar-id")
	public ResponseEntity<Tema> getById(@Valid @RequestParam Long id) {
		return temaService.findById(id);
	}

	@GetMapping("/buscar-descricao")
	public ResponseEntity<List<Tema>> getByDescricao(@RequestParam String descricao) {
		return temaService.findByDescricao(descricao);
	}

	@PostMapping("/cadastrar")
	public ResponseEntity<Tema> postTema(@Valid @RequestBody Tema tema) {
		return temaService.saveTema(tema);
	}

	@PutMapping("/alterar")
	public ResponseEntity<Tema> putTema(@Valid @RequestBody Tema tema) {
		return temaService.updateTema(tema);
	}

	@DeleteMapping("/deletar")
	public ResponseEntity<Tema> deleteTema(@RequestParam Long id) {
		return temaService.deletePostagem(id);
	}
	
}
