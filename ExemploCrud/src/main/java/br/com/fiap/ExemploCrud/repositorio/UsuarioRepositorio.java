package br.com.fiap.ExemploCrud.repositorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.fiap.ExemploCrud.SQLConection.ConexaoBD;
import br.com.fiap.ExemploCrud.model.User;

@Repository
public class UsuarioRepositorio {
	public User inserirUsuario(User usuario) {
		String sql = null;
		
		if(usuario.getId() > 0 ) {
			sql = "UPDATE usuarios u SET u.nome = ? , u.email = ? WHERE u.id = ?";
		} else {
			sql = "INSERT INTO usuarios (nome, email) VALUES (?, ?)";
		}
		
		try (Connection conn = ConexaoBD.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			if(usuario.getId() > 0 ) {				
				stmt.setString(1, usuario.getName());
				stmt.setString(2, usuario.getEmail());
				stmt.setInt(3, usuario.getId());
			} else {
				stmt.setString(1, usuario.getName());
				stmt.setString(2, usuario.getEmail());
			}			
			
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usuario;
	}

	public User buscarUsuario(int id) {
		String sql = "SELECT * FROM usuarios where id = ?";
		User usuario = new User();
		try {
			Connection conn = ConexaoBD.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			usuario.setId(id);
			usuario.setName(rs.getString("nome"));
			usuario.setEmail(rs.getString("email"));
			System.out.println("ID: " + id + ", Nome: " + usuario.getName() + ", Email: " + usuario.getEmail());

		} catch (SQLException e) {
			return usuario;
		}
		return usuario;
	}

	public List<User> listarUsuarios() {
		String sql = "SELECT * FROM usuarios";
		List<User> usuarios = new ArrayList<User>();
		try (Connection conn = ConexaoBD.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("nome");
				String email = rs.getString("email");
				String status = rs.getString("status");
				User usuario = new User(id, name, email, status);
				usuarios.add(usuario);
				System.out.println("ID: " + id + ", Name: " + name + ", Email: " + email + ", Status: " + status);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usuarios;
	}

	public void atualizarUsuario(int id, String name, String email) {
		String sql = "UPDATE usuarios SET nome = ?, email = ? WHERE id = ?";

		try {
			Connection conn = ConexaoBD.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, name);
			stmt.setString(2, email);
			stmt.setInt(3, id);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deletarUsuario(int id) {
		String sql = "DELETE FROM usuarios WHERE id = ?";

		try {
			Connection conn = ConexaoBD.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setInt(1, id);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void deletarTudo() {
		String sql = "DELETE FROM usuarios";

		try {
			Connection conn = ConexaoBD.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public void atualizarStatus(String status) {
		String sql = "UPDATE usuarios u SET u.status = ? WHERE u.status != 'ativo';";

		try {
			Connection conn = ConexaoBD.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, status);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
}