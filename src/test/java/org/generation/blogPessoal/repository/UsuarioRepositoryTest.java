package org.generation.blogPessoal.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.generation.blogPessoal.model.Usuario;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioRepositoryTest {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@BeforeAll
	void start() {
		Usuario usuario = new Usuario("João Gabriel", "joaozin", "12345");
		if(usuarioRepository.findByUsuario(usuario.getUsuario())!=null)
			usuarioRepository.save(usuario);
		
		usuario = new Usuario("Juliana", "juubs", "54321");
		if(usuarioRepository.findByUsuario(usuario.getUsuario())!=null)
			usuarioRepository.save(usuario);
	}
	
	@Test
	public void findByUsuarioRetornaUsuario() throws Exception {

		Usuario usuario = usuarioRepository.findByUsuario("joaozin").get();
		assertTrue(usuario.getUsuario().equals("joaozin"));
	}
	
	@AfterAll
	public void end() {
		usuarioRepository.deleteAll();
	}
}
