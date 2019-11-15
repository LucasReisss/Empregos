package br.unitins.empregos.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.empregos.application.Session;
import br.unitins.empregos.dao.FuncionarioDAO;
import br.unitins.empregos.model.Contratos;
import br.unitins.empregos.model.Funcionario;
import br.unitins.empregos.model.SelecionandoFuncionarios;

@Named
@ViewScoped
public class ContratarFuncionarioController implements Serializable {

	private static final long serialVersionUID = -2706580198038418115L;
	
	private String nome;
	private List<Funcionario> listaFuncionario = null;
	
	public void pesquisar() {
		
	}
	
	public void adicionar(int idFuncionario) {
		FuncionarioDAO dao = new FuncionarioDAO();
		Funcionario funcionario = dao.findById(idFuncionario);
		//verifica se existe um funcionario (de lista de contratos) na sessao
		Session.getInstance().setAttribute("funcionario", new ArrayList<SelecionandoFuncionarios>());
	}
	
	//obtendo o carrinho da sessao
	
	List<SelecionandoFuncionarios> carrinho = (ArrayList<SelecionandoFuncionarios>) Session.getInstance().getAttribute("carrinho");
	
	// criando um item de contrato para adicionar no carrinho
	
	Contratos contrato = new Contratos();
	
	
}
