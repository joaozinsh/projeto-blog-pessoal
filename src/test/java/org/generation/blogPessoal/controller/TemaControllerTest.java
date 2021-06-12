package org.generation.blogPessoal.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.generation.blogPessoal.model.Tema;
import org.generation.blogPessoal.model.Usuario;
import org.generation.blogPessoal.repository.TemaRepository;
import org.generation.blogPessoal.repository.UsuarioRepository;
import org.generation.blogPessoal.service.TemaService;
import org.generation.blogPessoal.service.UsuarioService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TemaControllerTest {

	@Autowired
	private TestRestTemplate testRestTemplate;
	
	@Autowired
	private TemaService temaService;
	
	@Autowired
	private TemaRepository temaRepository;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Tema tema;
	public Usuario usuario;
	
	@BeforeAll
	public void start() {
		usuario = new Usuario("João Gabriel", "joaozin", "12345");
			usuarioService.saveUsuario(usuario);
		tema = new Tema("Tema teste");
			temaService.saveTema(tema);
	}
	
	@Disabled
	@Test
	void deveRetornarListaDeTemasRetornaStatus200() {
		ResponseEntity<String> resposta = testRestTemplate.withBasicAuth("joaozin", "12345")
				.exchange("/temas", HttpMethod.GET, null, String.class);
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
	}
	
	@Disabled
	@Test
	void deveSalvarTemaRetornaStatus201() {
		Tema novoTema = new Tema("Novo tema teste");
		
		HttpEntity<Tema> request = new HttpEntity<Tema>(novoTema);
		
		ResponseEntity<Tema> resposta = testRestTemplate.withBasicAuth("joaozin", "12345")
				.exchange("/temas", HttpMethod.POST, request, Tema.class);
		assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
	}
	
	@Disabled
	@Test
	void deveAlterarTemaRtornaStatus201() {
		Tema alterTema = new Tema("Tema teste alterado");
		alterTema.setId((long) 1);
		
		HttpEntity<Tema> request = new HttpEntity<Tema>(alterTema);
		
		ResponseEntity<Tema> resposta = testRestTemplate.withBasicAuth("joaozin", "12345")
				.exchange("/temas", HttpMethod.PUT, request, Tema.class);
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
	}
	
	@AfterAll
	public void end() {
		usuarioRepository.deleteAll();
		temaRepository.deleteAll();
	}
}
