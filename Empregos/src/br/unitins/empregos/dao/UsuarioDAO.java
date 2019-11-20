package br.unitins.empregos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.unitins.empregos.model.Usuario;

public class UsuarioDAO extends DAO<Usuario>{
	public UsuarioDAO(Connection conn) {
		super(conn);
	}
	
	public UsuarioDAO() {
		//Cria uma nova conexao
		super(null);
	}
	
	public Usuario login(String login, String senha) {
		
		Connection conn = getConnection();
		
		try {
			PreparedStatement stat = conn.prepareStatement(
					"SELECT " +
					"  id, " +
					"  nome, " +
					"  login, " +
					"  senha " +					
					"FROM " +
					"  public.usuario " +
					"WHERE login = ? AND senha = ? ");
			
			stat.setString(1, login);
			stat.setString(2, senha);
			
			ResultSet rs = stat.executeQuery();
			
			Usuario usuario = null;
			
			if(rs.next()) {
				usuario = new Usuario();
				usuario.setId(rs.getInt("id"));
				usuario.setNome(rs.getString("nome"));
				usuario.setSobrenome(rs.getString("sobrenome"));
				usuario.setLogin(rs.getString("login"));
				usuario.setSenha(rs.getString("senha"));
			}
			
			return usuario;
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}

	@Override
	public void create(Usuario usuario) throws SQLException {	
			
		try {
			Connection  conn = getConnection();
			PreparedStatement stat;
			stat = conn.prepareStatement(
					"INSERT INTO " +
				    "public.usuario " +
				    " (nome, sobrenome, login, senha) " +
					"VALUES " +
				    " (?, ?, ?, ?) ", Statement.RETURN_GENERATED_KEYS);
			
			stat.setString(1, usuario.getNome());
			stat.setString(2, usuario.getSobrenome());
			stat.setString(3, usuario.getLogin());
			stat.setString(4, usuario.getSenha());
			
			stat.execute();
			
			
			// obtendo o id gerado pela tabela do banco de dados
			ResultSet rs = stat.getGeneratedKeys();
			rs.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void update(Usuario usuario) throws SQLException {
		Connection  conn = getConnection();
		
		PreparedStatement stat = conn.prepareStatement(
				"UPDATE public.usuario SET " +
			    " nome = ?, " +
			    " sobrenome = ?, " +		
			    " login = ?, " +
			    " senha = ? " +
				"WHERE " +
			    " id = ? ");
		stat.setString(1, usuario.getNome());
		stat.setString(2, usuario.getSobrenome());
		stat.setString(3, usuario.getLogin());
		stat.setString(4, usuario.getSenha());
		stat.setInt(5, usuario.getId());
			
		stat.execute();
			
	}

	@Override
	public boolean delete(int id) throws SQLException {

		Connection  conn = getConnection();
		if (conn == null) 
			return false;
		
		try {
			PreparedStatement stat = conn.prepareStatement(
					"DELETE FROM public.usuario WHERE id = ?");
			stat.setInt(1, id);
			
			stat.execute();
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Usuario> findAll() {
		Connection conn = getConnection();
		if (conn == null) 
			return null;
		
		try {
			PreparedStatement stat = conn.prepareStatement(
					"SELECT " +
					"  id, " +
					"  nome, " +
					"  sobrenome, " +
					"  login, " +
					"  senha  " +				
					"FROM " +
					"  public.usuario ");
			ResultSet rs = stat.executeQuery();
			
			List<Usuario> listaUsuario = new ArrayList<Usuario>();
			
			while(rs.next()) {
				Usuario usuario = new Usuario();
				usuario.setId(rs.getInt("id"));
				usuario.setNome(rs.getString("nome"));
				usuario.setSobrenome(rs.getString("sobrenome"));
				usuario.setLogin(rs.getString("login"));
				usuario.setSenha(rs.getString("senha"));
				
				listaUsuario.add(usuario);
			}
			
			if (listaUsuario.isEmpty())
				return null;
			return listaUsuario;
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Usuario findId(Integer id) {
		Connection conn = getConnection();
		if (conn == null) 
			return null;
		
		try {
			PreparedStatement stat = conn.prepareStatement(
					"SELECT " +
					"  id, " +
					"  nome, " +
					"  sobrenome, " +
					"  login, " +
					"  senha " +				
					"FROM " +
					"  public.usuario " +
					"WHERE id = ? ");
			
			stat.setInt(1, id);
			
			ResultSet rs = stat.executeQuery();
			
			Usuario usuario = null;
			
			if(rs.next()) {
				usuario = new Usuario();
				usuario.setId(rs.getInt("id"));
				usuario.setNome(rs.getString("nome"));
				usuario.setSobrenome(rs.getString("sobrenome"));
				usuario.setLogin(rs.getString("login"));
				usuario.setSenha(rs.getString("senha"));
			}
			
			return usuario;
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
