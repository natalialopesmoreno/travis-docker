package br.DreamTeam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.DreamTeam.model.TemaModel;

public interface TemaRepository extends JpaRepository<TemaModel, Long> {


	List<TemaModel> findByNomeContainingIgnoreCase(String nome);

}
