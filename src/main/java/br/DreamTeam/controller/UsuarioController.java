package br.DreamTeam.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import br.DreamTeam.model.UsuarioLogin;
import br.DreamTeam.model.UsuarioModel;
import br.DreamTeam.repository.UsuarioRepository;
import br.DreamTeam.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController implements WebMvcConfigurer {

	public void addViewControllers(ViewControllerRegistry index) {
		index.addViewController("/").setViewName("forward:/index.html");
	}

	// INJETOU O REPOSITÓRIO DO SERVICO PARA COMUNICAR COM O BANCO DE DADOS
	@Autowired
	private UsuarioRepository repository;

	@Autowired
	private UsuarioService usuarioService;

	// CRUD
	// CREATE --> POST
	@PostMapping
	public ResponseEntity<UsuarioModel> criar(@RequestBody UsuarioModel usuario) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(usuario));
	}

	@PostMapping("/logar")
	public ResponseEntity<UsuarioLogin> Autentication(@RequestBody Optional<UsuarioLogin> user) {
		return usuarioService.Logar(user).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}

	@PostMapping("/cadastrar")
	public ResponseEntity<UsuarioModel> Post(@RequestBody UsuarioModel usuario) {
		Optional<UsuarioModel> user = usuarioService.CadastrarUsuario(usuario);
		try {
			return ResponseEntity.ok(user.get());
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}

	}

	// READ --> GET
	@GetMapping
	public ResponseEntity<List<UsuarioModel>> buscarTodos() {
		return ResponseEntity.ok(repository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<UsuarioModel> buscarUm(@PathVariable Long id) {
		return repository.findById(id).map(usuarioId -> ResponseEntity.ok(usuarioId))
				.orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<UsuarioModel>> buscarPorNome(@PathVariable String nome) {
		return ResponseEntity.ok(repository.findByNomeContainingIgnoreCase(nome));
	}

	// UPDATE --> PUT
	@PutMapping("/{id}")
	public ResponseEntity<UsuarioModel> atualizar(@PathVariable Long id, @RequestBody UsuarioModel usuario) {
		usuario.setId_usuario(id);
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(usuario));
	}

	// DELETE --> DELETE
	@DeleteMapping("/{id}")
	public void remover(@PathVariable Long id) {
		repository.deleteById(id);
	}

}
