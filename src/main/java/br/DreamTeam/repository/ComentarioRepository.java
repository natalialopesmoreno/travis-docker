package br.DreamTeam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.DreamTeam.model.ComentarioModel;

@Repository
public interface ComentarioRepository extends JpaRepository<ComentarioModel, Long>{

}
