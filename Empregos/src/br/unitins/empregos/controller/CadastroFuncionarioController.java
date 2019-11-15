package br.unitins.empregos.controller;

import java.io.Serializable;
import java.sql.SQLException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.unitins.empregos.application.Util;
import br.unitins.empregos.dao.FuncionarioDAO;
import br.unitins.empregos.model.Funcionario;

@Named
@RequestScoped
public class CadastroFuncionarioController implements Serializable {

	private static final long serialVersionUID = 8591675034952113387L;
	
	Funcionario funcionario = new Funcionario();

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	
	public void cadastrar() {
		FuncionarioDAO funcionario = new FuncionarioDAO();
		
		try {
			getFuncionario().setSenha(Util.hashSHA256(getFuncionario().getSenha()));
			funcionario.create(getFuncionario());
			funcionario.getConnection().commit();
			Util.addMessageInfo("Inclusão realizada com sucesso.");
			limpar();
		} catch (SQLException e) {
			funcionario.rollbackConnection();
			funcionario.closeConnection();
			Util.addMessageError("Erro ao incluir o Usuario no Banco de Dados");
			e.printStackTrace();
		}
	}
	
	public void limpar() {
		funcionario = null;
	}

}
