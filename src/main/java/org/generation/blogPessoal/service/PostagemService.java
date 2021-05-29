package org.generation.blogPessoal.service;

import java.util.Optional;

import org.generation.blogPessoal.model.Postagem;
import org.generation.blogPessoal.repository.PostagemRepository;
import org.generation.blogPessoal.repository.TemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostagemService {
	
	@Autowired
	private PostagemRepository repository;
	@Autowired
	private TemaRepository repositoryTema;

	public Optional<Postagem> checkPostagem(Postagem postagem) {
		if (postagem.getTitulo().isEmpty() || postagem.getTexto().isEmpty() || repositoryTema.findById(postagem.getId()).isEmpty()) {
			return Optional.empty();
		} else {
			return Optional.of(repository.save(postagem));
		}
	}
}
