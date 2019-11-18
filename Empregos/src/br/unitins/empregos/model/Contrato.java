package br.unitins.empregos.model;

import java.time.LocalDate;
import java.util.List;

public class Contrato {
	private Integer id;
	private LocalDate data;
	private Usuario usuario;
	
	private List<ItemContrato> listaFuncionario;
	
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<ItemContrato> getListaSeleFuncionario() {
		return listaFuncionario;
	}

	public void setListaSeleFuncionario(List<ItemContrato> listaFuncionario) {
		this.listaFuncionario = listaFuncionario;
	}
	
	

}
