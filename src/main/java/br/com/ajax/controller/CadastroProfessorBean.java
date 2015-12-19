package br.com.ajax.controller;

import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ajax.model.Professor;
import br.com.ajax.service.CadastroProfessorService;
import br.com.ajax.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroProfessorBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CadastroProfessorService cadastroProfessorService;

	private Professor professor;

	public CadastroProfessorBean() {
		limpar();
	}

	public void salvar() {
		this.cadastroProfessorService.salvar(professor);
		limpar();
		FacesUtil.addInfoMessage("Professor cadastrado com sucesso.");
	}

	public void limpar() {
		professor = new Professor();
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public boolean isEditando() {
		return this.professor.getId() != null;
	}

}
