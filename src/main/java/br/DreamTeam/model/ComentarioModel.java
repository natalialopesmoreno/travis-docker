package br.DreamTeam.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_comentario")
public class ComentarioModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_comentario;

	// CHAVES ESTRANGEIRAS
	@ManyToOne
	@JsonIgnoreProperties({"comentarios","postagem"})
	@NotNull
	private UsuarioModel usuario;

	@ManyToOne
	@JsonIgnoreProperties({"comentarios"})
	@NotNull
	private PostagemModel postagem;

	@Column
	@NotNull
	private String artigo;// VERIFICAR SE NÃO TEM NENHUM TIPO TEXT

	@Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date data ;

	@Column
	private Boolean curtir;

	// GETTERS AND SETTERS

	public UsuarioModel getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioModel usuario) {
		this.usuario = usuario;
	}

	public PostagemModel getPostagem() {
		return postagem;
	}

	public void setPostagem(PostagemModel postagem) {
		this.postagem = postagem;
	}

	public String getArtigo() {
		return artigo;
	}

	public void setArtigo(String artigo) {
		this.artigo = artigo;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Boolean getCurtir() {
		return curtir;
	}

	public void setCurtir(Boolean curtir) {
		this.curtir = curtir;
	}

	public Long getId_comentario() {
		return id_comentario;
	}

	public void setId_comentario(Long id_comentario) {
		this.id_comentario = id_comentario;
	}

}