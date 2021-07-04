package org.generation.blogPessoal.controller;

import java.util.List;

import javax.validation.Valid;

import org.generation.blogPessoal.model.UserLogin;
import org.generation.blogPessoal.model.Usuario;
import org.generation.blogPessoal.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	
	@GetMapping("/buscar-todos")
	public ResponseEntity<List<Usuario>> getAll(){
		return usuarioService.findAll();
	}
	
	@PostMapping("/login")
	public ResponseEntity<UserLogin> loginUsuario(@Valid @RequestBody UserLogin user) {
		return usuarioService.login(user);
	}

	@PostMapping("/cadastrar")
	public ResponseEntity<Usuario> postUsuario(@Valid @RequestBody Usuario usuario) {
		return usuarioService.saveUsuario(usuario);
	}
	
	@DeleteMapping("/deletar")
	public ResponseEntity<Usuario> deleteUsuario(@RequestParam Long id) {
		return usuarioService.deleteUsuario(id);
	}

}
