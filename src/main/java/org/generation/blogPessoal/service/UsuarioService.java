package org.generation.blogPessoal.service;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.generation.blogPessoal.model.UserLogin;
import org.generation.blogPessoal.model.Usuario;
import org.generation.blogPessoal.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	
	/**
	 * Metodo para buscar todos os usuarios
	 * @return ResponseEntity com o status HTTP e uma lista de todos os usuarios
	 */
	public ResponseEntity<List<Usuario>> findAll() {
		List<Usuario> listaDeUsuario = usuarioRepository.findAll();
		if (listaDeUsuario.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(listaDeUsuario);
		}
	}
	
	/**
	 * Metodo para salvar um usuario na base de dados encriptando a sua senha
	 * @param novoUsuario
	 * @return ResponseEntity com o status HTTP e o usuario criado
	 */
	public ResponseEntity<Usuario> saveUsuario(Usuario novoUsuario) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Optional<Usuario> usuarioExistente = usuarioRepository.findByUsuario(novoUsuario.getUsuario());
		
		if (usuarioExistente.isEmpty()) {
			novoUsuario.setSenha(encoder.encode(novoUsuario.getSenha()));
			return ResponseEntity.status(201).body(usuarioRepository.save(novoUsuario));
		} else {
			return ResponseEntity.status(400).build();
		}
	}

	/**
	 * Metodo para fazer login
	 * @param user
	 * @return ResponseEntity com o status HTTP e o token de autenticação
	 */
	public ResponseEntity<UserLogin> login(UserLogin user) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Optional<Usuario> usuario = usuarioRepository.findByUsuario(user.getUsuario());

		if (usuario.isPresent()) {
			if (encoder.matches(user.getSenha(), usuario.get().getSenha())) {
				String auth = user.getUsuario() + ":" + user.getSenha();
				byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String(encodedAuth);

				user.setToken(authHeader);
				user.setId(usuario.get().getId());
				user.setNome(usuario.get().getNome());
				user.setFoto(usuario.get().getFoto());
				user.setTipo(usuario.get().getTipo());

				return ResponseEntity.status(202).body(user);
			}

		}
		return ResponseEntity.status(401).build();
	}
	
	/**
	 * Metodo para deletar um usuario caso ele exista no banco de dados
	 * @param id
	 * @return ResponseEntity com o status HTTP da requisição
	 */
	public ResponseEntity<Usuario> deleteUsuario(Long id) {	
		if (usuarioRepository.existsById(id)) {
			usuarioRepository.deleteById(id);
		} else {
			return ResponseEntity.status(404).build();
		}
		return null;
	}
}
