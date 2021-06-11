package org.generation.blogPessoal.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.generation.blogPessoal.model.Tema;
import org.generation.blogPessoal.model.Usuario;
import org.generation.blogPessoal.repository.TemaRepository;
import org.generation.blogPessoal.repository.UsuarioRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TemaControllerTest {

	@Autowired
	private TestRestTemplate testRestTemplate;
	
	@Autowired
	private TemaRepository temaRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Tema tema;
	public Usuario usuario;
	
	@BeforeAll
	public void start() {
		usuario = new Usuario("João Gabriel", "joaozin", "12345");
			usuarioRepository.save(usuario);
		tema = new Tema("Tema teste");
			temaRepository.save(tema);
	}
	
	@Test
	void deveRetornarListaDeTemasRetornaStatus200() {
		ResponseEntity<String> resposta = testRestTemplate.withBasicAuth("joaozin", "12345")
				.exchange("/temas", HttpMethod.GET, null, String.class);
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
	}
	
	@Test
	void deveSalvarTemaRetornaStatus200() {
		ResponseEntity<String> resposta = testRestTemplate.withBasicAuth("joaozin", "12345")
				.exchange("/temas", HttpMethod.POST, null, String.class);
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
	}
	
	@Test
	void deveAlterarTemaRtornaStatus200() {
		ResponseEntity<String> resposta = testRestTemplate.withBasicAuth("joaozin", "12345")
				.exchange("/tema", HttpMethod.PUT, null, String.class);
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
	}
	
	@AfterAll
	public void end() {
		usuarioRepository.deleteAll();
		temaRepository.deleteAll();
	}
}
