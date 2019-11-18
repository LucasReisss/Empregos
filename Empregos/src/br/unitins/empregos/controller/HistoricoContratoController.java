package br.unitins.empregos.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.empregos.application.Session;
import br.unitins.empregos.dao.ContratoDAO;
import br.unitins.empregos.model.Contrato;
import br.unitins.empregos.model.Usuario;

@Named
@ViewScoped
public class HistoricoContratoController implements Serializable {

	private static final long serialVersionUID = -6544068802652697270L;
	
	private List<Contrato> listaContrato = null;

	public List<Contrato> getListaContrato() {
		
		if(listaContrato == null) {
			ContratoDAO dao = new ContratoDAO();
			Usuario usuario = (Usuario) Session.getInstance().getAttribute("usuarioLogado");
			listaContrato = dao.findByUsuario(usuario.getId());
			if(listaContrato == null)
				listaContrato = new ArrayList<Contrato>();
			dao.closeConnection();
		}
		
		return listaContrato;
	}
	
	public String detalhes(Contrato contrato) {
	Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
	flash.put("delheContrato", contrato);
//	detalhesContrato().getFlash()
		return "detalhescontrato.xhtml?faces-redirect=true";
	}
	
	
}
