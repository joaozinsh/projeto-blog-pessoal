package org.generation.blogPessoal.service;

import java.util.Optional;

import org.generation.blogPessoal.model.Tema;
import org.generation.blogPessoal.repository.TemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TemaService {

	@Autowired
	private TemaRepository repository;

	public Optional<Tema> cadastrarTema(Tema novoTema) {
		Optional<Tema> temaExistente = repository.findByDescricaoIgnoreCase(novoTema.getDescricao());
		if (temaExistente.isPresent()) {
			return Optional.empty();
		} else {
			return Optional.of(repository.save(novoTema));
		}
	}

}
