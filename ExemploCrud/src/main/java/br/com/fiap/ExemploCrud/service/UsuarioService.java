package br.com.fiap.ExemploCrud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.ExemploCrud.model.User;
import br.com.fiap.ExemploCrud.repositorio.UsuarioRepositorio;

@Service
public class UsuarioService {
	
	@Autowired	
	private UsuarioRepositorio repositorio;

	public User criar(User usuario) {
		return repositorio.inserirUsuario(usuario);
	}

	public User ler(int id) {
		return repositorio.buscarUsuario(id);
	}

	public List<User> lerTodos() {
		return repositorio.listarUsuarios();
	}

	public User atualizar(User usuario) {
		User existente = ler(usuario.getId());
		if (existente != null) {
			existente.setName(usuario.getName());
			existente.setEmail(usuario.getEmail());
		}
		repositorio.atualizarUsuario(usuario.getId(), usuario.getName(), usuario.getEmail());
		return existente;
	}
	
	public void atualizarStatus(String status) {		
		repositorio.atualizarStatus(status);
	}

	public void deletar(int id) {
		repositorio.deletarUsuario(id);
	}
}
