package org.generation.blogPessoal.service;

import java.util.List;
import java.util.Optional;

import org.generation.blogPessoal.model.Tema;
import org.generation.blogPessoal.repository.TemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TemaService {

	@Autowired
	private TemaRepository temaRepository;

	/**
	 * Metodo para buscar todos os temas
	 * @return ResponseEntity com o status HTTP e uma lista de todos os temas
	 */
	public ResponseEntity<List<Tema>> findAll() {
		List<Tema> listaDeTema = temaRepository.findAll();
		if (listaDeTema.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(listaDeTema);
		}
	}
	
	/**
	 * Metodo para buscar um tema por ID
	 * @param id
	 * @return ResponseEntity com o status HTTP e o tema
	 */
	public ResponseEntity<Tema> findById(Long id) {
		return temaRepository.findById(id).map(resp -> ResponseEntity.status(200).body(resp))
				.orElse(ResponseEntity.status(404).build());
	}
	
	/**
	 * Metodo para buscar temas pela descrição
	 * @param descricao
	 * @return ResponseEntity com o status HTTP e os temas
	 */
	public ResponseEntity<List<Tema>> findByDescricao(String descricao) {
		List<Tema> listaPorDescricao = temaRepository.findAllByDescricaoContainingIgnoreCase(descricao);
		if (listaPorDescricao.isEmpty()) {
			return ResponseEntity.status(404).build();
		} else {
			return ResponseEntity.status(200).body(listaPorDescricao);
		}
	}
	
	/**
	 * Metodo para salvar um tema
	 * @param novoTema
	 * @return ResponseEntity com o status HTTP e o novo tema
	 */
	public ResponseEntity<Tema> saveTema(Tema novoTema) {
		Optional<Tema> temaExistente = temaRepository.findByDescricaoIgnoreCase(novoTema.getDescricao());
		if (temaExistente.isPresent()) {
			return ResponseEntity.status(400).build();
		} else {
			return ResponseEntity.status(201).body(temaRepository.save(novoTema));
		}
	}

	/**
	 * Metodo para alterar um tema
	 * @param alterTema
	 * @return ResponseEntity com o status HTTP e o tema alterado
	 */
	public ResponseEntity<Tema> updateTema(Tema alterTema) {
		Optional<Tema> temaExistente = temaRepository.findByDescricaoIgnoreCase(alterTema.getDescricao());
		if (temaExistente.isPresent()) {
			return ResponseEntity.status(400).build();
		} else {
			return ResponseEntity.status(200).body(temaRepository.save(alterTema));
		}
	}
	
	/**
	 * Metodo para apagar um tema
	 * @param id
	 * @return ResponseEntity com o status HTTP
	 */
	public ResponseEntity<Tema> deletePostagem(Long id) {
		if (temaRepository.existsById(id)) {
			temaRepository.deleteById(id);
		} else {
			return ResponseEntity.status(404).build();
		}
		return null;
	}
	
}
