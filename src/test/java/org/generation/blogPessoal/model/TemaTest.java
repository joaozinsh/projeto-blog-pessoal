package org.generation.blogPessoal.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TemaTest {

	public Tema tema;
	
	@Autowired
	private  ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	Validator validator = factory.getValidator();
	
	@BeforeEach
	public void start() {
		tema = new Tema("Tema teste");
	}
	
	@Test
	void testValidaAtributos() {
		Set<ConstraintViolation<Tema>> violation = validator.validate(tema);
		System.out.println(violation.toString());
		assertTrue(violation.isEmpty());
	}
	
	@Test
	void testValidaAtributosNulos() {
		Tema temaErro = new Tema();
		temaErro.setDescricao("");
		Set<ConstraintViolation<Tema>> violation = validator.validate(temaErro);
		System.out.println(violation.toString());
		assertFalse(violation.isEmpty());
	}
}
