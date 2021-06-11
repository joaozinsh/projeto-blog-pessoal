package org.generation.blogPessoal.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.generation.blogPessoal.model.Postagem;
import org.generation.blogPessoal.model.Tema;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PostagemRepositoryTest {

	@Autowired
	private PostagemRepository postagemRepository;
	
	@Autowired
	private TemaRepository temaRepository;
	
	public Postagem postagem;
	public Tema tema;
	
	@BeforeAll
	void start() {
		tema = new Tema("Tema teste");
			temaRepository.save(tema);
		
		postagem = new Postagem("Postagem Teste", "Uma postagem de teste", tema);
			postagemRepository.save(postagem);
		
		postagem = new Postagem("Postagem Teste 2", "Outra postagem de teste", tema);
			postagemRepository.save(postagem);
	}
	
	@Test
	public void findAllByTituloContainingIgnoreCaseRetornaDuasPostagens() throws Exception {

		List<Postagem> listaDePostagens = postagemRepository.findAllByTituloContainingIgnoreCase("Postagem");
		assertEquals(2, listaDePostagens.size());
	}
	
	@AfterAll
	public void end() {
		postagemRepository.deleteAll();
		temaRepository.deleteAll();
	}
}
