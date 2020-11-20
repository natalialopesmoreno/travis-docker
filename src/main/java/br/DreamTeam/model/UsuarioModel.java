package br.DreamTeam.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_usuario")
public class UsuarioModel 
{
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id_usuario;
	

	private String nome;
	private String foto;
	private String usuario;
	private String senha;
	
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
	@JsonIgnoreProperties({"usuario","comentarios"})
	private List<PostagemModel> postagem;
	
	@OneToMany(mappedBy = "usuario",cascade = CascadeType.ALL)
	@JsonIgnoreProperties({"usuario"})
	private List<ComentarioModel> comentarios;
	
	
	//GETTERS AND SETTERS
	
	public Long getId_usuario() {
		return Id_usuario;
	}
	public void setId_usuario(Long id_usuario) {
		Id_usuario = id_usuario;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String login) {
		this.usuario = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public List<PostagemModel> getPostagem() {
		return postagem;
	}
	public void setPostagem(List<PostagemModel> postagens) {
		this.postagem = postagens;
	}
	public List<ComentarioModel> getComentarios() {
		return comentarios;
	}
	public void setComentarios(List<ComentarioModel> comentarios) {
		this.comentarios = comentarios;
	}
	
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	
}
