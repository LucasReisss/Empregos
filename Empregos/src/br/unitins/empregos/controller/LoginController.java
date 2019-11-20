package br.unitins.empregos.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.unitins.empregos.application.Session;
import br.unitins.empregos.application.Util;
import br.unitins.empregos.dao.UsuarioDAO;
import br.unitins.empregos.model.Usuario;

@Named
@RequestScoped
public class LoginController implements Serializable {

	private static final long serialVersionUID = -8798700712323623367L;

	private Usuario usuario = new Usuario();

	public String login() {
		UsuarioDAO usuarioDAO = new UsuarioDAO();

		List<Usuario> usuarios = new ArrayList<Usuario>();
		usuarios = usuarioDAO.findAll();

		for (Usuario usuarioDaLista : usuarios) {
			if (getUsuario().getLogin().equals(usuarioDaLista.getLogin())) {
				getUsuario().setSenha(Util.hashSHA256(getUsuario().getSenha()));
				if (getUsuario().getSenha().equals(usuarioDaLista.getSenha())) {
					System.out.println("igual");

					if (usuario != null) {
						// armazenando um usuario na sessao
						Session.getInstance().setAttribute("usuarioLogado", usuario);
						return "perfilusuario.xhtml?faces-redirect=true";

					}

				}
			}
		}
		Util.addMessageError("Usuário ou senha Inválido.");
		return null;

	}

	public Usuario getUsuario() {
		if(usuario == null)
			usuario = new Usuario();

		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
