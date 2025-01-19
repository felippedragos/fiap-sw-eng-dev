package br.com.fiap.ExemploCrud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.ExemploCrud.RabbitMQ.StatusPublisher;
import br.com.fiap.ExemploCrud.model.User;
import br.com.fiap.ExemploCrud.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "http://localhost:8081") 
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@PostMapping
	@Operation(summary = "Criar um novo usuário")
	public ResponseEntity<User> criarUsuario(@RequestBody User usuario) {
		User novoUsuario = usuarioService.criar(usuario);
		StatusPublisher publisher = new StatusPublisher(); 
		try {
			publisher.publishStatus("Ativo");
		} catch (Exception e) {
			e.printStackTrace();
		}
		//StatusConsumer consumer = new StatusConsumer();
		//consumer.receiveStatus("Ativo");
		return ResponseEntity.ok(novoUsuario);
	}

	@GetMapping("/{id}")
	@Operation(summary = "Buscar um usuário")
	public ResponseEntity<User> lerUsuario(@PathVariable int id) {
		User usuario = usuarioService.ler(id);
		if (usuario == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(usuario);
	}

	@GetMapping
	@Operation(summary = "Listar todos os usuarios cadastrados")
	public ResponseEntity<List<User>> lerTodosUsuarios() {
		List<User> usuarios = usuarioService.lerTodos();
		return ResponseEntity.ok(usuarios);
	}

	@PutMapping("/{id}")
	@Operation(summary = "Atualizar dados de um usuarios")
	public ResponseEntity<User> atualizarUsuario(@PathVariable int id, @RequestBody User usuario) {
		usuario.setId(id);
		User usuarioAtualizado = usuarioService.atualizar(usuario);
		if (usuarioAtualizado == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(usuarioAtualizado);
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Deletar usuário cadastrado")
	public ResponseEntity<Void> deletarUsuario(@PathVariable int id) {
		usuarioService.deletar(id);
		return ResponseEntity.noContent().build();
	}
}
