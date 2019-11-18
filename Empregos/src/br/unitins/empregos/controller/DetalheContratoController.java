package br.unitins.empregos.controller;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.empregos.model.Contrato;

@Named
@ViewScoped
public class DetalheContratoController implements Serializable {

	private static final long serialVersionUID = -9197853768654313586L;

	private Contrato contrato;

	public DetalheContratoController() {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.keep("detalheContrato");
		contrato = (Contrato) flash.get("detalheContrato");
	}

	public Contrato getContrato() {
		return contrato;
	}

	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}

}
