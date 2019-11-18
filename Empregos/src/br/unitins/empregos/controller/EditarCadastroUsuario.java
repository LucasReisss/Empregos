package br.unitins.empregos.controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.unitins.empregos.model.Usuario;

@Named
@SessionScoped
public class EditarCadastroUsuario implements Serializable {

	private static final long serialVersionUID = 8676646516801596911L;
	
	private Usuario usuario;
	
	
}
