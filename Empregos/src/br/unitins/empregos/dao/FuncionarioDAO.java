package br.unitins.empregos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.unitins.empregos.model.Funcionario;

public class FuncionarioDAO extends DAO<Funcionario>{
	public FuncionarioDAO(Connection conn) {
		super(conn);

	}
	
	public FuncionarioDAO() {
		//Cria uma nova conexao
		super(null);
	}
	
	public Funcionario login(String login, String senha) {
		
		Connection conn = getConnection();
		
		try {
			PreparedStatement stat = conn.prepareStatement(
					"SELECT " +
					"  id, " +
					"  nome, " +
					"  login, " +
					"  senha, " +					
					"FROM " +
					"  public.funcionario " +
					"WHERE login = ? AND senha = ? ");
			
			stat.setString(1, login);
			stat.setString(2, senha);
			
			ResultSet rs = stat.executeQuery();
			
			Funcionario funcionario = null;
			
			if(rs.next()) {
				funcionario = new Funcionario();
				funcionario.setId(rs.getInt("id"));
				funcionario.setNome(rs.getString("nome"));
				funcionario.setNome(rs.getString("sobrenome"));
				funcionario.setLogin(rs.getString("login"));
				funcionario.setSenha(rs.getString("senha"));
			}
			
			return funcionario;
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}

	@Override
	public void create(Funcionario funcionario) throws SQLException {
		try {
		Connection  conn = getConnection();
			
		PreparedStatement stat = conn.prepareStatement(
				"INSERT INTO " +
			    "public.funcionario " +
			    " (nome, sobrenome, login, senha, cpf, profissao, genero) " +
				"VALUES " +
			    " (?, ?, ?, ?, ?, ?, ?) ", Statement.RETURN_GENERATED_KEYS);
		stat.setString(1, funcionario.getNome());
		stat.setString(2, funcionario.getSobrenome());
		stat.setString(3, funcionario.getLogin());
		stat.setString(4, funcionario.getSenha());
		stat.setString(5, funcionario.getCpf());
		stat.setString(6, funcionario.getProfissao());
		stat.setString(7, funcionario.getGenero());
		
		stat.execute();
		
		// obtendo o id gerado pela tabela do banco de dados
		ResultSet rs = stat.getGeneratedKeys();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void update(Funcionario funcionario) throws SQLException {
		Connection  conn = getConnection();
		
		PreparedStatement stat = conn.prepareStatement(
				"UPDATE public.funcionario SET " +
			    " nome = ?, " +
			    " sobrenome = ?, " +		
			    " login = ?, " +
			    " senha = ?, " +
			    " cpf = ?, " +
			    " profissao = ?, " +
			    " genero = ? " +
				"WHERE " +
			    " id = ? ");
		stat.setString(1, funcionario.getNome());
		stat.setString(2, funcionario.getSobrenome());
		stat.setString(3, funcionario.getLogin());
		stat.setString(4, funcionario.getSenha());
		stat.setString(5, funcionario.getCpf());
		stat.setString(6, funcionario.getProfissao());
		stat.setString(7, funcionario.getGenero());
		stat.setInt(8, funcionario.getId());
			
		stat.execute();
			
	}

	@Override
	public boolean delete(int id) {

		Connection  conn = getConnection();
		if (conn == null) 
			return false;
		
		try {
			PreparedStatement stat = conn.prepareStatement(
					"DELETE FROM public.funcionario WHERE id = ?");
			stat.setInt(1, id);
			
			stat.execute();
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Funcionario> findAll() {
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
					"  senha,  " +
					"  cpf,  " +
					"  profissao,  " +
					"  genero  " +
					"FROM " +
					"  public.funcionario ");
			ResultSet rs = stat.executeQuery();
			
			List<Funcionario> listaFuncionario = new ArrayList<Funcionario>();
			
			while(rs.next()) {
				Funcionario funcionario = new Funcionario();
				funcionario.setId(rs.getInt("id"));
				funcionario.setNome(rs.getString("nome"));
				funcionario.setSobrenome(rs.getString("sobrenome"));
				funcionario.setLogin(rs.getString("login"));
				funcionario.setSenha(rs.getString("senha"));
				funcionario.setCpf(rs.getString("cpf"));
				funcionario.setProfissao(rs.getString("profissao"));
				funcionario.setGenero(rs.getString("genero"));
				
				listaFuncionario.add(funcionario);
			}
			
			if (listaFuncionario.isEmpty())
				return null;
			return listaFuncionario;
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Funcionario findById(Integer id) {
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
					"  public.funcionario " +
					"WHERE id = ? ");
			
			stat.setInt(1, id);
			
			ResultSet rs = stat.executeQuery();
			
			Funcionario funcionario = null;
			
			if(rs.next()) {
				funcionario = new Funcionario();
				funcionario.setId(rs.getInt("id"));
				funcionario.setNome(rs.getString("nome"));
				funcionario.setSobrenome(rs.getString("sobrenome"));
				funcionario.setLogin(rs.getString("login"));
				funcionario.setSenha(rs.getString("senha"));
			}
			
			return funcionario;
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
