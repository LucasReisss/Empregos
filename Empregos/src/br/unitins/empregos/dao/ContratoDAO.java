package br.unitins.empregos.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.unitins.empregos.model.Contrato;
import br.unitins.empregos.model.ItemContrato;
import br.unitins.empregos.model.Usuario;

public class ContratoDAO extends DAO<Contrato> {

	public ContratoDAO(Connection conn) {
		super(conn);
	}
	
	public ContratoDAO() {
		super(null);
	}

	@Override
	public void create(Contrato contrato) throws SQLException {
		
		Connection  conn = getConnection();
		
		PreparedStatement stat = conn.prepareStatement(
				"INSERT INTO " +
			    "public.contrato " +
			    " (data, idusuario) " +
				"VALUES " +
			    " (?, ?) ", Statement.RETURN_GENERATED_KEYS);
		stat.setDate(1, Date.valueOf(contrato.getData()));
		stat.setInt(2, contrato.getUsuario().getId());
		
		stat.execute();
		
		// obtendo o id gerado pela tabela do banco de dados
		ResultSet rs = stat.getGeneratedKeys();
		rs.next();
		contrato.setId(rs.getInt("id"));
		// inserindo os itens de contrato
		// compartilhando a conexao para ficar na mesma transacao
		ItemContratoDAO dao = new ItemContratoDAO(conn);
		for (ItemContrato itemContrato : contrato.getListaSeleFuncionario()) {
			// informando quem eh o pai da crianca
			itemContrato.setContrato(contrato);
			// salvando no banco de dados
			dao.create(itemContrato);
		}
		
	}

	@Override
	public void update(Contrato entity) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean delete(int id) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}
	
	public List<Contrato> findByUsuario(int idUsuario) {
		Connection conn = getConnection();
		if (conn == null) 
			return null;
		
		try {
			PreparedStatement stat = conn.prepareStatement(
					"SELECT " +
					"  c.id, " +
					"  c.data, " +
					"  u.id as idusuario, " +
					"  u.nome, "+
					"  u.login,  "+
					"  u.senha "+				
					"FROM " +
					"  public.contrato c, " +
					"  public.usuario u " +
					"WHERE " +
					"  c.idusuario = u.id AND " +
					"  u.id = ? ");
			stat.setInt(1, idUsuario);
			
			ResultSet rs = stat.executeQuery();
			
			List<Contrato> listaContrato = new ArrayList<Contrato>();
			
			while(rs.next()) {
				Contrato contrato = new Contrato();
				contrato.setId(rs.getInt("id"));
				contrato.setData(rs.getDate("data").toLocalDate());
				contrato.setUsuario(new Usuario());
				contrato.getUsuario().setId(rs.getInt("idusuario"));
				contrato.getUsuario().setNome(rs.getString("nome"));
				contrato.getUsuario().setLogin(rs.getString("login"));
				contrato.getUsuario().setSenha(rs.getString("senha"));
				// e os itens de contrato?!!?
				ItemContratoDAO dao = new ItemContratoDAO(conn);
				contrato.setListaSeleFuncionario(dao.findByContratos(contrato));
				
				listaContrato.add(contrato);
			}
			
			if (listaContrato.isEmpty())
				return null;
			return listaContrato;
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;	
	}

	@Override
	public List<Contrato> findAll() {
		
		Connection conn = getConnection();
		if (conn == null) 
			return null;
		
		try {
			PreparedStatement stat = conn.prepareStatement(
					"SELECT " +
					"  c.id, " +
					"  c.data, " +
					"  u.idusuario, " +
					"  u.nome, "+
					"  u.login,  "+
					"  u.senha, "+				
					"FROM " +
					"  public.contrato c, " +
					"  public.usuario u " +
					"WHERE " +
					"  c.idusuario = u.id ");
			ResultSet rs = stat.executeQuery();
			
			List<Contrato> listaContrato = new ArrayList<Contrato>();
			
			while(rs.next()) {
				Contrato contrato = new Contrato();
				contrato.setId(rs.getInt("id"));
				contrato.setData( rs.getDate("data").toLocalDate() );
				contrato.setUsuario(new Usuario());
				contrato.getUsuario().setId(rs.getInt("idusuario"));
				contrato.getUsuario().setNome(rs.getString("nome"));
				contrato.getUsuario().setLogin(rs.getString("login"));
				contrato.getUsuario().setSenha(rs.getString("senha"));
				// e os itens de contrato?!!?
				//contrato.setListaItemContrato(listaItemContrato);
				
				
				listaContrato.add(contrato);
			}
			
			if (listaContrato.isEmpty())
				return null;
			return listaContrato;
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public Contrato findById(Integer id) {
		Connection conn = getConnection();
		if (conn == null) 
			return null;
		
		try {
			PreparedStatement stat = conn.prepareStatement(
					"SELECT " +
					"  id, " +
					"  nome " +
					"FROM " +
					"  public.contrato " +
					"WHERE id = ? ");
			
			stat.setInt(1, id);
			
			ResultSet rs = stat.executeQuery();
			
			Contrato contrato = null;
			
			if(rs.next()) {
				contrato = new Contrato();
				contrato.setId(rs.getInt("id"));
			}
			
			return contrato;
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
