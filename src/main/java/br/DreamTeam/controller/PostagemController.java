package br.DreamTeam.controller;

import java.util.List;

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

import br.DreamTeam.model.PostagemModel;
import br.DreamTeam.model.TemaModel;
import br.DreamTeam.repository.PostagemRepository;

@RestController
@RequestMapping("/postagem")
public class PostagemController implements WebMvcConfigurer
{


	public void addViewControllers(ViewControllerRegistry index) {
		index.addViewController("/").setViewName("forward:/index.html");
	}
	

		//INJETOU O REPOSITÓRIO DO SERVICO PARA COMUNICAR COM O BANCO DE DADOS
			@Autowired
			private PostagemRepository repository;
			
		
			//CRUD
			//CREATE --> POST
			@PostMapping
			public ResponseEntity<PostagemModel> criar(@RequestBody PostagemModel postagem)
			{
				return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(postagem));	
			}
			
			//READ --> GET
			@GetMapping 
			public ResponseEntity<List<PostagemModel>> buscarTodos() 
			{		
				return ResponseEntity.ok(repository.findAll());
			}
			
			@GetMapping ("/{id}")
			public ResponseEntity<PostagemModel> buscarUm(@PathVariable Long id) 
			{		
				return repository.findById(id)
						.map(postagemId -> ResponseEntity.ok(postagemId))
						.orElse(ResponseEntity.notFound().build());
			}
			
			@GetMapping ("/tema/{tema}")
			public ResponseEntity<List<PostagemModel>> buscarPorTema(@PathVariable TemaModel tema) 
			{		
				return ResponseEntity.ok(repository.findByTema(tema));
						
			}
			
			@GetMapping ("/titulo/{titulo}")
			public ResponseEntity<List<PostagemModel>> buscarPorTitulo(@PathVariable String titulo) 
			{		
				return ResponseEntity.ok(repository.findByTituloContainingIgnoreCase(titulo));
						
			}
			
			
			
			//UPDATE --> PUT
			@PutMapping("/{id}")
			public ResponseEntity<PostagemModel> atualizar(@PathVariable Long id, @RequestBody PostagemModel postagem) 
			{
				postagem.setId_postagem(id);
				return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(postagem));
			}
			
			//DELETE --> DELETE
			@DeleteMapping ("/{id}")
			public void remover(@PathVariable Long id) 
			{		
				repository.deleteById(id);
			}
			


}




