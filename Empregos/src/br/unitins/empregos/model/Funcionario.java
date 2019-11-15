package br.unitins.empregos.model;

import java.time.LocalDate;

import javax.validation.constraints.Past;

public class Funcionario extends Usuario {
	@Past
	private LocalDate dataAniversario;
	private String cpf;
	private String profissao;
	private String genero;
	public LocalDate getDataAniversario() {
		return dataAniversario;
	}
	public void setDataAniversario(LocalDate dataAniversario) {
		this.dataAniversario = dataAniversario;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getProfissao() {
		return profissao;
	}
	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	
	
}
