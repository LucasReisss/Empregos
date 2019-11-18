package br.unitins.empregos.model;

public class ItemContrato {
	private Integer id;
	private Funcionario funcionario;
	private Contrato contratos;

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

	public Contrato getContratos() {
		return contratos;
	}

	public void setContratos(Contrato contratos) {
		this.contratos = contratos;
	}

}
