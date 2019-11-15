package br.unitins.empregos.model;

import java.time.LocalDate;
import java.util.List;

public class Contratos {
	private Integer id;
	private LocalDate data;
	private Funcionario funcionario;
	
	private List<SelecionandoFuncionarios> listaSeleFuncionario;
	
	

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

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public List<SelecionandoFuncionarios> getListaSeleFuncionario() {
		return listaSeleFuncionario;
	}

	public void setListaSeleFuncionario(List<SelecionandoFuncionarios> listaSeleFuncionario) {
		this.listaSeleFuncionario = listaSeleFuncionario;
	}
	
	

}
