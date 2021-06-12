package org.generation.blogPessoal.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.generation.blogPessoal.model.Postagem;
import org.generation.blogPessoal.model.Tema;
import org.generation.blogPessoal.model.Usuario;
import org.generation.blogPessoal.repository.PostagemRepository;
import org.generation.blogPessoal.repository.TemaRepository;
import org.generation.blogPessoal.repository.UsuarioRepository;
import org.generation.blogPessoal.service.PostagemService;
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
public class PostagemControllerTest {

	@Autowired
	private TestRestTemplate testRestTemplate;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private PostagemRepository postagemRepository;
	
	@Autowired
	private PostagemService postagemService;
	
	@Autowired
	private TemaRepository temaRepository;
	
	@Autowired
	private TemaService temaService;
	
	public Postagem postagem;
	public Tema tema;
	public Usuario usuario;
	
	@BeforeAll
	public void start() {
		usuario = new Usuario("João Gabriel", "joaozin", "12345");
			usuarioService.saveUsuario(usuario);
		tema = new Tema("Tema teste");
			temaService.saveTema(tema);
		postagem = new Postagem("Postagem Teste", "Uma postagem de teste", tema);
			postagemService.savePostagem(postagem);
	}
	
	@Disabled
	@Test
	void deveRetornarListaDePostagensRetornaStatus200() {
		ResponseEntity<String> resposta = testRestTemplate.withBasicAuth("joaozin", "12345")
				.exchange("/postagens", HttpMethod.GET, null, String.class);
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
	}
	
	@Disabled
	@Test
	void deveSalvarPostagemRetornaStatus201() {
		Postagem novaPostagem = new Postagem("Nova Postagem Teste", "Outra postagem de teste", tema);
		
		HttpEntity<Postagem> request = new HttpEntity<Postagem>(novaPostagem);
		
		ResponseEntity<Postagem> resposta = testRestTemplate.withBasicAuth("joaozin", "12345")
				.exchange("/postagens", HttpMethod.POST, request, Postagem.class);
		assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
	}
	
	@Disabled
	@Test
	void deveAlterarPostagemRetornaStatus201() {
		Postagem alterPostagem = new Postagem("Alterando Postagem Teste", "Uma postagem de teste", tema);
		
		HttpEntity<Postagem> request = new HttpEntity<Postagem>(alterPostagem);
		System.out.println(alterPostagem.getId());
		
		ResponseEntity<Postagem> resposta = testRestTemplate.withBasicAuth("joaozin", "12345")
				.exchange("/postagens", HttpMethod.PUT, request, Postagem.class);
		assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
	}
	
	
	@AfterAll
	public void end() {
		usuarioRepository.deleteAll();
		postagemRepository.deleteAll();
		temaRepository.deleteAll();
	}
}
