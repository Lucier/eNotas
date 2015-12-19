package br.com.ajax.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ajax.model.Sala;
import br.com.ajax.repository.SalaRepository;
import br.com.ajax.repository.filter.SalaFilter;
import br.com.ajax.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaSalaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private SalaRepository salaRepository;

	private SalaFilter salaFilter;
	private List<Sala> salasFiltradas;
	private Sala salaSelecionada;

	public PesquisaSalaBean() {
		salaFilter = new SalaFilter();
		salasFiltradas = new ArrayList<>();
	}

	public void pesquisar() {
		salasFiltradas = salaRepository.salasFiltradas(salaFilter);
	}

	public void excluir() {
		salaRepository.remover(salaSelecionada);
		salasFiltradas.remove(salaSelecionada);

		FacesUtil.addInfoMessage("Sala " + salaSelecionada.getNome() + " excluída com sucesso.");
	}

	public SalaRepository getSalaRepository() {
		return salaRepository;
	}

	public void setSalaRepository(SalaRepository salaRepository) {
		this.salaRepository = salaRepository;
	}

	public SalaFilter getSalaFilter() {
		return salaFilter;
	}

	public void setSalaFilter(SalaFilter salaFilter) {
		this.salaFilter = salaFilter;
	}

	public List<Sala> getSalasFiltradas() {
		return salasFiltradas;
	}

	public void setSalasFiltradas(List<Sala> salasFiltradas) {
		this.salasFiltradas = salasFiltradas;
	}

	public Sala getSalaSelecionada() {
		return salaSelecionada;
	}

	public void setSalaSelecionada(Sala salaSelecionada) {
		this.salaSelecionada = salaSelecionada;
	}

}
