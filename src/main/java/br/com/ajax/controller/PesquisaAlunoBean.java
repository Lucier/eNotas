package br.com.ajax.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ajax.model.Aluno;
import br.com.ajax.repository.AlunoRepository;
import br.com.ajax.repository.filter.AlunoFilter;
import br.com.ajax.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaAlunoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private AlunoRepository alunoRepository;

	private AlunoFilter alunoFilter;
	private List<Aluno> alunosFiltrados;
	private Aluno alunoSelecionado;

	public PesquisaAlunoBean() {
		alunoFilter = new AlunoFilter();
		alunosFiltrados = new ArrayList<>();
	}

	public void pesquisar() {
		alunosFiltrados = alunoRepository.alunosFiltrados(alunoFilter);
	}

	public void excluir() {
		alunoRepository.remover(alunoSelecionado);
		alunosFiltrados.remove(alunoSelecionado);

		FacesUtil.addInfoMessage("Aluno " + alunoSelecionado.getNome() + " excluído com sucesso.");
	}

	public AlunoFilter getAlunoFilter() {
		return alunoFilter;
	}

	public void setAlunoFilter(AlunoFilter alunoFilter) {
		this.alunoFilter = alunoFilter;
	}

	public List<Aluno> getAlunosFiltrados() {
		return alunosFiltrados;
	}

	public void setAlunosFiltrados(List<Aluno> alunosFiltrados) {
		this.alunosFiltrados = alunosFiltrados;
	}

	public Aluno getAlunoSelecionado() {
		return alunoSelecionado;
	}

	public void setAlunoSelecionado(Aluno alunoSelecionado) {
		this.alunoSelecionado = alunoSelecionado;
	}

}
