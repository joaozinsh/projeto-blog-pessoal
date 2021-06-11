package org.generation.blogPessoal.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.generation.blogPessoal.model.Postagem;
import org.generation.blogPessoal.model.Tema;
import org.generation.blogPessoal.model.Usuario;
import org.generation.blogPessoal.repository.PostagemRepository;
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
public class PostagemControllerTest {

	@Autowired
	private TestRestTemplate testRestTemplate;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PostagemRepository postagemRepository;
	
	@Autowired
	private TemaRepository temaRepository;
	
	public Postagem postagem;
	public Tema tema;
	public Usuario usuario;
	
	@BeforeAll
	public void start() {
		usuario = new Usuario("João Gabriel", "joaozin", "12345");
			usuarioRepository.save(usuario);
		tema = new Tema("Tema teste");
			temaRepository.save(tema);
		postagem = new Postagem("Postagem Teste", "Uma postagem de teste", tema);
			postagemRepository.save(postagem);
	}
	
	@Test
	void deveRetornarListaDePostagensRetornaStatus200() {
		ResponseEntity<String> resposta = testRestTemplate.withBasicAuth("joaozin", "12345")
				.exchange("/postagens", HttpMethod.GET, null, String.class);
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
	}
	
	@Test
	void deveSalvarPostagemRetornaStatus200() {
		ResponseEntity<String> resposta = testRestTemplate.withBasicAuth("joaozin", "12345")
				.exchange("/postagens", HttpMethod.POST, null, String.class);
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
	}
	
	@Test
	void deveAlterarPostagemRetornaStatus200() {
		ResponseEntity<String> resposta = testRestTemplate.withBasicAuth("joaozin", "12345")
				.exchange("/postagens", HttpMethod.PUT, null, String.class);
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
	}
	
	
	@AfterAll
	public void end() {
		usuarioRepository.deleteAll();
		postagemRepository.deleteAll();
		temaRepository.deleteAll();
	}
}
