package br.unitins.empregos.controller;

import java.io.Serializable;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.empregos.application.Session;
import br.unitins.empregos.application.Util;
import br.unitins.empregos.dao.ContratoDAO;
import br.unitins.empregos.model.Contrato;
import br.unitins.empregos.model.ItemContrato;
import br.unitins.empregos.model.Usuario;

@Named
@ViewScoped
public class CarrinhoContratosController implements Serializable {

	private static final long serialVersionUID = -4845442413205947916L;
	

	private Contrato contrato;

	public Contrato getContratos() {
		if (contrato == null)
			contrato = new Contrato();

		// obtendo o carrinhoContrato da sessao
		List<ItemContrato> carrinhoContrato = (ArrayList<ItemContrato>) Session.getInstance()
				.getAttribute("carrinhoContrato");

		// adicionando os itens do carrinhoContrato no contrato
		if (carrinhoContrato == null)
			carrinhoContrato = new ArrayList<ItemContrato>();
		contrato.setListaSeleFuncionario(carrinhoContrato);

		return contrato;
	}

	public void remover(int idFuncionario) {
		Usuario usuario = (Usuario) Session.getInstance().getAttribute("usuarioLogado");
		if (usuario == null)
			Util.addMessageWarn("Eh preciso estar logado para realizar uma pedido. Faca o Login!!");
		else {
			List<ItemContrato> carrinhoContrato = (List<ItemContrato>) Session.getInstance()
					.getAttribute("carrinhoContrato");

			for (ItemContrato itemContrato : carrinhoContrato) {
				if (itemContrato.getFuncionario().getId().equals(idFuncionario)) {
					carrinhoContrato.remove(itemContrato);
					return;
				}
			}
		}
	}

	public void finalizar() {
		Usuario usuario = (Usuario) Session.getInstance().getAttribute("usuarioLogado");
		if (usuario == null) {
			Util.addMessageWarn("Eh preciso estar logado para realizar uma contrato. Faca o Login!!");
			return;
		}
		// montar o contrato
		Contrato contrato = new Contrato();
		contrato.setData(LocalDate.now());
		contrato.setUsuario(usuario);
		List<ItemContrato> carrinhoContrato = (ArrayList<ItemContrato>) Session.getInstance()
				.getAttribute("carrinhoContrato");
		contrato.setListaSeleFuncionario(carrinhoContrato);
		// salvar no banco
		ContratoDAO dao = new ContratoDAO();
		try {
			dao.create(contrato);
			dao.getConnection().commit();
			Util.addMessageInfo("Contrato realizado com sucesso.");
			// limpando o carrinhoContrato
			Session.getInstance().setAttribute("carrinhoContrato", null);
		} catch (SQLException e) {
			dao.rollbackConnection();
			dao.closeConnection();
			Util.addMessageInfo("Erro ao finalizar o Contrato.");
			e.printStackTrace();
		}

	}

	public void setContratos(Contrato contrato) {

		this.contrato = contrato;
	}

}
