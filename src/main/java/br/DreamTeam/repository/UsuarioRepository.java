package br.DreamTeam.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.DreamTeam.model.UsuarioModel;

public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long> 
{

	
	public List<UsuarioModel> findAllByNomeContainingIgnoreCase (String nome);
	public Optional<UsuarioModel> findByUsuarioAndSenha(String usuario, String senha);
	public Optional<UsuarioModel> findByUsuario(String usuario);
	public Optional<UsuarioModel> findById(Long id);
	public List<UsuarioModel> findByNomeContainingIgnoreCase(String nome);

	

	

}
