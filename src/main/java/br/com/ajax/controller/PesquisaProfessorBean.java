package br.com.ajax.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ajax.model.Professor;
import br.com.ajax.repository.ProfessorRepository;
import br.com.ajax.repository.filter.ProfessorFilter;
import br.com.ajax.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaProfessorBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ProfessorRepository professorRepository;

	private ProfessorFilter professorFilter;
	private List<Professor> professoresFiltrados;
	private Professor professorSelecionado;

	public PesquisaProfessorBean() {
		professorFilter = new ProfessorFilter();
		professoresFiltrados = new ArrayList<>();
	}

	public void pesquisar() {
		professoresFiltrados = professorRepository.professoresFiltrados(professorFilter);
	}

	public void excluir() {
		professorRepository.remover(professorSelecionado);
		professoresFiltrados.remove(professorSelecionado);

		FacesUtil.addInfoMessage("Professor " + professorSelecionado.getNome() + " excluído com sucesso.");
	}

	public ProfessorFilter getProfessorFilter() {
		return professorFilter;
	}

	public void setProfessorFilter(ProfessorFilter professorFilter) {
		this.professorFilter = professorFilter;
	}

	public List<Professor> getProfessoresFiltrados() {
		return professoresFiltrados;
	}

	public void setProfessoresFiltrados(List<Professor> professoresFiltrados) {
		this.professoresFiltrados = professoresFiltrados;
	}

	public Professor getProfessorSelecionado() {
		return professorSelecionado;
	}

	public void setProfessorSelecionado(Professor professorSelecionado) {
		this.professorSelecionado = professorSelecionado;
	}

}
