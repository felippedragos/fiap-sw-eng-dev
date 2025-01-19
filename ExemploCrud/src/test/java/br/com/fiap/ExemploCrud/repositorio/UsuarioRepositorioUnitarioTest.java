package br.com.fiap.ExemploCrud.repositorio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.fiap.ExemploCrud.model.User;

public class UsuarioRepositorioUnitarioTest {
	private UsuarioRepositorio usuarioRepositorio;

	@BeforeEach
	void setUp() {
		usuarioRepositorio = new UsuarioRepositorio();
	}

	@Test
	void testCriarLerUsuario() {
		User usuario = new User();
		usuario.setId(1);
		usuario.setName("João");
		usuario.setEmail("joao@exemplo.com");

		usuarioRepositorio.inserirUsuario(usuario);

		User usuarioLido = usuarioRepositorio.buscarUsuario(1);
		assertNotNull(usuarioLido);
		assertEquals("João", usuarioLido.getName());
	}

	@Test
	void testListarUsuarios() {
		List<User> usuarios = usuarioRepositorio.listarUsuarios();
		assertNotNull(usuarios);
	}

	@Test
	void testAtualizarUsuario() {
		User usuario = new User();
		usuario.setId(10);
		usuario.setName("João");
		usuario.setEmail("joao@exemplo.com");

		usuarioRepositorio.inserirUsuario(usuario);

		usuario.setName("João Atualizado");
		usuarioRepositorio.atualizarUsuario(usuario.getId(), usuario.getName(), usuario.getEmail());

		User usuarioAtualizado = usuarioRepositorio.buscarUsuario(10);
		assertEquals("João Atualizado", usuarioAtualizado.getName());
	}

	@Test
	void testDeletarUsuario() {
		User usuario = new User();
		usuario.setId(100);
		usuario.setName("João");
		usuario.setEmail("joao@exemplo.com");

		usuarioRepositorio.inserirUsuario(usuario);
		usuarioRepositorio.deletarUsuario(100);

		User usuarioDeletado = usuarioRepositorio.buscarUsuario(100);
		assertNull(usuarioDeletado.getName());
	}
}
