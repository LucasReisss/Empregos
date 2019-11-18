package br.unitins.empregos.controller;

import java.io.Serializable;
import java.sql.SQLException;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.empregos.application.Util;
import br.unitins.empregos.dao.DAO;
import br.unitins.empregos.dao.MensagemDAO;
import br.unitins.empregos.model.Mensagem;

@Named
@ViewScoped
public class MensagemController implements Serializable {

	private static final long serialVersionUID = 8632880274488504991L;

	private Mensagem mensagem;

	public MensagemController() {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.keep("mensagemFlash");
		mensagem = (Mensagem) flash.get("mensagemFlash");
	}

	public void incluir() {
		DAO<Mensagem> dao = new MensagemDAO();
		// faz a inclusao do banco de dados
		try {
			dao.create(getMensagem());
			dao.getConnection().commit();
			Util.addMessageInfo("Alteção realizada com sucesso");
			limpar();
		} catch (SQLException e) {
			dao.rollbackConnection();
			Util.addMessageInfo("Erro ao excluir o produto no Banco de Dados.");
			e.printStackTrace();
		}finally {
			dao.closeConnection();
		}
	}

	public Mensagem getMensagem() {
		if(mensagem == null) {
			mensagem = new Mensagem();
		}
		return mensagem;
	}

	public void setMensagem(Mensagem mensagem) {
		this.mensagem = mensagem;
	}
	
	public void limpar() {
		mensagem = null;
	}

}
