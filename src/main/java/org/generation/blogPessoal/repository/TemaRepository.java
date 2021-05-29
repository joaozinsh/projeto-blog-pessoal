package org.generation.blogPessoal.repository;

import java.util.Optional;

import org.generation.blogPessoal.model.Tema;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TemaRepository extends JpaRepository<Tema, Long> {

	public Optional<Tema> findByDescricaoContainingIgnoreCase(String descricao);
}
