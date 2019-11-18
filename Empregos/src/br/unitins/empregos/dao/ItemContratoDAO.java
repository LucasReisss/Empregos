package br.unitins.empregos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.unitins.empregos.model.Contrato;
import br.unitins.empregos.model.Funcionario;
import br.unitins.empregos.model.ItemContrato;


public class ItemContratoDAO extends DAO<ItemContrato> {

	public ItemContratoDAO(Connection conn) {
		super(conn);
	}
	
	public ItemContratoDAO() {
		// cria uma nova conexao
		super(null);
	}

	@Override
	public void create(ItemContrato itemContratos) throws SQLException {
		Connection  conn = getConnection();
		
		PreparedStatement stat = conn.prepareStatement(
				"INSERT INTO " +
			    "public.itemContratos " +
			    " (idcontratos, idfuncionario) " +
				"VALUES " +
			    " (?, ?) ");
		stat.setInt(1, itemContratos.getContratos().getId());
		stat.setInt(2, itemContratos.getFuncionario().getId());
	
		stat.execute();
		
	}

	@Override
	public void update(ItemContrato entity) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean delete(int id) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<ItemContrato> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<ItemContrato> findByContratos(Contrato contrato) {
		Connection conn = getConnection();
		if (conn == null) 
			return null;
		
		try {
			PreparedStatement stat = conn.prepareStatement(
					"SELECT " +
					"  c.id, " +
					"  c.idfuncionario, " +
					"  f.nome " +
					"FROM " +
					"  public.itemcontrato c, " +
					"  public.funcionario f " +
					"WHERE " +
					"  v.idfuncionario = p.id AND " +
					"  v.idcontrato = ? ");
			
			stat.setInt(1, contrato.getId());
			ResultSet rs = stat.executeQuery();
			
			List<ItemContrato> listaItemContrato = new ArrayList<ItemContrato>();
			
			while(rs.next()) {
				ItemContrato item = new ItemContrato();
				item.setId(rs.getInt("id"));
				
				Funcionario funcionario = new Funcionario();
				funcionario.setId(rs.getInt("idfuncionario"));
				funcionario.setNome(rs.getString("nome"));
				
				item.setFuncionario(funcionario);
				
				item.setContratos(contrato);
				
				listaItemContrato.add(item);
			}
			
			if (listaItemContrato.isEmpty())
				return null;
			return listaItemContrato;
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
