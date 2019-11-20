package br.unitins.empregos.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.unitins.empregos.application.Session;
import br.unitins.empregos.application.Util;
import br.unitins.empregos.dao.FuncionarioDAO;
import br.unitins.empregos.model.Funcionario;

@Named
@RequestScoped
public class LoginFuncionarioController implements Serializable {

	private static final long serialVersionUID = -4283759291775987674L;

	private Funcionario funcionario = new Funcionario();
	
	public String entrar() {
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		
		List<Funcionario>funcionarios = new ArrayList<Funcionario>();
		 funcionarios = funcionarioDAO.findAll();
		 
		 for (Funcionario funcionarioDaLista : funcionarios) {
			if(getFuncionario().getLogin().equals(funcionarioDaLista.getLogin())) {
				getFuncionario().setSenha(Util.hashSHA256(getFuncionario().getSenha()));
				if(getFuncionario().getSenha().equals(funcionarioDaLista.getSenha())){
					System.out.println("igual");
					
					if(funcionario != null) {
						// armazenando um usuario na sessao
						Session.getInstance().setAttribute("usuarioLogado", funcionario);
						return "perfilfuncionario.xhtml?faces-redirect=true";
					}
					
				}
			}
		}
		 Util.addMessageError("Usuário ou senha Inválido.");
		 return null;

	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	
	
	
}
