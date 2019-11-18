package br.unitins.empregos.controller;

import java.io.Serializable;
import java.sql.SQLException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.unitins.empregos.application.Util;
import br.unitins.empregos.dao.UsuarioDAO;
import br.unitins.empregos.model.Usuario;

@Named
@RequestScoped
public class CadastroController implements Serializable {

	private static final long serialVersionUID = -265575143550736213L;
	
	Usuario usuario = new Usuario();

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public void cadastrar() {
		UsuarioDAO usuario = new UsuarioDAO();
		getUsuario().setSenha(Util.hashSHA256(getUsuario().getSenha()));
		usuario.create(getUsuario());
		try {
			usuario.getConnection().commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
