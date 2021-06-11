package org.generation.blogPessoal.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

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
public class TemaRepositoryTest {

	@Autowired
	private TemaRepository temaRepository;
	
	public Tema tema;
	
	@BeforeAll
	void start() {
		tema = new Tema("Tema teste");
			temaRepository.save(tema);
			
		tema = new Tema("Tema teste 2");
			temaRepository.save(tema);
	}
	
	@Test
	public void findAllByDescricaoContainingIgnoreCaseRetornaDoisTemas() throws Exception {

		List<Tema> listaDeTemas = temaRepository.findAllByDescricaoContainingIgnoreCase("Tema teste");
		assertEquals(2, listaDeTemas.size());
	}
	
	@Test
	public void findByDescricaoIgnoreCaseRetornaTema() throws Exception {

		Tema tema = temaRepository.findByDescricaoIgnoreCase("Tema teste").get();
		assertTrue(tema.getDescricao().equals("Tema teste"));
	}
	
	@AfterAll
	public void end() {
		temaRepository.deleteAll();
	}
}
