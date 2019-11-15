package br.unitins.empregos.model;

public class SelecionandoFuncionarios {
	private Integer id;
	private Funcionario funcionario;
	private Contratos contratos;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Contratos getContratos() {
		return contratos;
	}

	public void setContratos(Contratos contratos) {
		this.contratos = contratos;
	}

}
