package br.com.ajax.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ajax.model.Disciplina;
import br.com.ajax.repository.DisciplinaRepository;
import br.com.ajax.repository.filter.DisciplinaFilter;
import br.com.ajax.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaDisciplinaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private DisciplinaRepository disciplinaRepository;

	private DisciplinaFilter disciplinaFilter;
	private List<Disciplina> disciplinasFiltradas;
	private Disciplina disciplinaSelecionada;

	public PesquisaDisciplinaBean() {
		disciplinaFilter = new DisciplinaFilter();
		disciplinasFiltradas = new ArrayList<>();
	}

	public void pesquisar() {
		disciplinasFiltradas = disciplinaRepository.disciplinasFiltradas(disciplinaFilter);
	}

	public void excluir() {
		disciplinaRepository.remover(disciplinaSelecionada);
		disciplinasFiltradas.remove(disciplinaSelecionada);

		FacesUtil.addInfoMessage("Disciplian " + disciplinaSelecionada.getNome() + " excluída com sucesso.");

	}

	public DisciplinaFilter getDisciplinaFilter() {
		return disciplinaFilter;
	}

	public void setDisciplinaFilter(DisciplinaFilter disciplinaFilter) {
		this.disciplinaFilter = disciplinaFilter;
	}

	public List<Disciplina> getDisciplinasFiltradas() {
		return disciplinasFiltradas;
	}

	public void setDisciplinasFiltradas(List<Disciplina> disciplinasFiltradas) {
		this.disciplinasFiltradas = disciplinasFiltradas;
	}

	public Disciplina getDisciplinaSelecionada() {
		return disciplinaSelecionada;
	}

	public void setDisciplinaSelecionada(Disciplina disciplinaSelecionada) {
		this.disciplinaSelecionada = disciplinaSelecionada;
	}

}
