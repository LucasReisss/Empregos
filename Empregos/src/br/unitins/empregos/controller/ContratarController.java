package br.unitins.empregos.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.empregos.application.Session;
import br.unitins.empregos.application.Util;
import br.unitins.empregos.dao.FuncionarioDAO;
import br.unitins.empregos.model.Funcionario;
import br.unitins.empregos.model.ItemContrato;

@Named
@ViewScoped
public class ContratarController implements Serializable {

	private static final long serialVersionUID = -2706580198038418115L;

	private String nome;
	private List<Funcionario> listaFuncionario = null;

	public void pesquisar() {
		listaFuncionario = null;
	}

	public void adicionar(int idFuncionario) {
		FuncionarioDAO dao = new FuncionarioDAO();
		Funcionario funcionario = dao.findById(idFuncionario);
		// verificar se existe um carrinho na sessão
		if (Session.getInstance().getAttribute("carrinhoContrato") == null) {
			// adicionar um carrinho (de itens de contrato) na sessao
			Session.getInstance().setAttribute("carrinhoContrato", new ArrayList<ItemContrato>());
		}

		// obtendo carrinho da sessao
		List<ItemContrato> carrinhoContrato = (ArrayList<ItemContrato>) Session.getInstance().getAttribute("carrinhoContrato");

		// criando um item de contrato para adicionar no carrinho
		ItemContrato item = new ItemContrato();
		item.setFuncionario(funcionario);

		// adicionado o item no carrrinho (variavel local)
		carrinhoContrato.add(item);

		// atualizando o carrinho na sessao
		Session.getInstance().setAttribute("carrinhoContrato", carrinhoContrato);

		Util.addMessageInfo("Funcionario adicionado no carrrinho Contrato. " + "Quantidade de Funcionarios: "
				+ carrinhoContrato.size());

	}
	
	public List<Funcionario> getListaFuncionario() {
		if(listaFuncionario == null) {
			FuncionarioDAO dao = new  FuncionarioDAO();
			listaFuncionario = dao.findByNome(getNome());
			if(listaFuncionario == null) {
				listaFuncionario = new ArrayList<Funcionario>();
				dao.closeConnection();
			}
		}
		return listaFuncionario;
	}


	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
