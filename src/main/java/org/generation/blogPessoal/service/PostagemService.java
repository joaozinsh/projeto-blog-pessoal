package org.generation.blogPessoal.service;

import java.util.List;

import org.generation.blogPessoal.model.Postagem;
import org.generation.blogPessoal.repository.PostagemRepository;
import org.generation.blogPessoal.repository.TemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PostagemService {

	@Autowired
	private PostagemRepository postagemRepository;
	
	@Autowired
	private TemaRepository temaRepository;
	
	/**
	 * Metodo para buscar todas as postagens
	 * @return ResponseEntity com o status HTTP e uma lista de todas as postagens
	 */
	public ResponseEntity<List<Postagem>> findAll() {
		List<Postagem> listaDePostagem = postagemRepository.findAll();
		if (listaDePostagem.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(listaDePostagem);
		}
	}
	
	/**
	 * Metodo para buscar uma postagem por ID
	 * @param id
	 * @return ResponseEntity com o status HTTP e a postagem
	 */
	public ResponseEntity<Postagem> findById(Long id) {
		return postagemRepository.findById(id).map(resp -> ResponseEntity.status(200).body(resp))
				.orElse(ResponseEntity.status(404).build());
	}
	
	/**
	 * Metodo para buscar postagens pelo titulo
	 * @param titulo
	 * @return ResponseEntity com o status HTTP e as postagens
	 */
	public ResponseEntity<List<Postagem>> findByTitulo(String titulo) {
		List<Postagem> listaPorTitulo = postagemRepository.findAllByTituloContainingIgnoreCase(titulo);
		if (listaPorTitulo.isEmpty()) {
			return ResponseEntity.status(404).build();
		} else {
			return ResponseEntity.status(200).body(listaPorTitulo);
		}
	}
	
	/**
	 * Metodo para salvar uma postagem
	 * @param novaPostagem
	 * @return ResponseEntity com o status HTTP e a nova postagem
	 */
	public ResponseEntity<Postagem> savePostagem(Postagem novaPostagem) {
		if (temaRepository.existsById(novaPostagem.getTema().getId())) {
			return ResponseEntity.status(201).body(postagemRepository.save(novaPostagem));
		} else {
			return ResponseEntity.status(400).build();
		}
	}
	
	/**
	 * Metodo para alterar uma postagem
	 * @param alterPostagem
	 * @return ResponseEntity com o status HTTP e a postagem alterada
	 */
	public ResponseEntity<Postagem> updatePostagem(Postagem alterPostagem) {
		if (postagemRepository.existsById(alterPostagem.getId())
				&& temaRepository.existsById(alterPostagem.getTema().getId())) {
			return ResponseEntity.status(200).body(postagemRepository.save(alterPostagem));
		} else {
			return ResponseEntity.status(404).build();
		}
	}
	
	/**
	 * Metodo para apagar uma postagem
	 * @param id
	 * @return ResponseEntity com o status HTTP
	 */
	public ResponseEntity<Postagem> deletePostagem(Long id) {
		if (postagemRepository.existsById(id)) {
			postagemRepository.deleteById(id);
		} else {
			return ResponseEntity.status(404).build();
		}
		return null;
	}
}
