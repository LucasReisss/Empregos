package br.unitins.empregos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.unitins.empregos.model.Mensagem;

public class MensagemDAO extends DAO<Mensagem> {
	
	public MensagemDAO(Connection conn) {
		super(conn);
	}
	
	public MensagemDAO() {
		super(null);
	}
	
	@Override
	public void create(Mensagem mensagem) throws SQLException{
		Connection conn = getConnection();
		
		PreparedStatement stat = conn.prepareStatement(
				"INSERT INTO " +
				"public.mensagem " +
				"(titulo, assunto)" +
				"VALUES " +
				"(?, ? )");
		stat.setString(1, mensagem.getTitulo());
		stat.setString(2, mensagem.getAssunto());
	}
	
	@Override
	public void update(Mensagem mensagem) throws SQLException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public boolean delete(int id) throws SQLException{
		/*
		 * Connection conn = getConnection();
		 * 
		 * PreparedStatement stat = conn.prepareStatement(
		 * "DELETE FROM public.mensagem WHERE id = ?"); stat.setInt(1, id);
		 * 
		 * stat.execute();
		 */
		return false;
	}



	@Override
	public List<Mensagem> findAll() {
		Connection conn = getConnection();
		if(conn == null)
			return null;
		
		try {
			PreparedStatement stat = conn.prepareStatement(
					"SELECT " +
					"id, " +
					"titulo, " +		
					"assunto " +
					"FROM " + 
					" public.mensagem ");
			ResultSet rs = stat.executeQuery();
			
			List<Mensagem> listaMensagem = new ArrayList<Mensagem>();
			
			while(rs.next()) {
				Mensagem mensagem = new Mensagem();
				mensagem.setId(rs.getInt("id"));
				mensagem.setTitulo(rs.getString("titulo"));
				
				listaMensagem.add(mensagem);
			}
			
			if (listaMensagem.isEmpty())
				return null;
			return listaMensagem;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Mensagem> findByTitulo(String titulo) {
		Connection conn = getConnection();
		if (conn == null) 
			return null;
		
		try {
			PreparedStatement stat = conn.prepareStatement(
					"SELECT " +
					"  id, " +
					"  assunto " +
					"FROM " +
					"  public.mensagem " +
					"WHERE " +
					"  titulo ilike ? ");
			
			stat.setString(1, titulo == null ? "%" : "%"+titulo+"%");
			ResultSet rs = stat.executeQuery();
			
			List<Mensagem> listaMensagem = new ArrayList<Mensagem>();
			
			while(rs.next()) {
				Mensagem mensagem = new Mensagem();
				mensagem.setId(rs.getInt("id"));
				mensagem.setTitulo(rs.getString("titulo"));
				mensagem.setAssunto(rs.getString("asunto"));
				
				
				listaMensagem.add(mensagem);
			}
			
			if (listaMensagem.isEmpty())
				return null;
			return listaMensagem;
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Mensagem findById(Integer id) {
		Connection conn = getConnection();
		if (conn == null) 
			return null;
		
		try {
			PreparedStatement stat = conn.prepareStatement(
					"SELECT " +
					"  id, " +
					"  titulo, " +
					"  assunto " +
					"FROM " +
					"  public.mensagem " +
					"WHERE id = ? ");
			
			stat.setInt(1, id);
			
			ResultSet rs = stat.executeQuery();
			
			Mensagem mensagem = null;
			
			if(rs.next()) {
				mensagem = new Mensagem();
				mensagem.setId(rs.getInt("id"));
				mensagem.setTitulo(rs.getString("titulo"));
				mensagem.setAssunto(rs.getString("assunto"));
			}
			
			return mensagem;
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
}
