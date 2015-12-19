package br.com.ajax.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ajax.model.Curso;
import br.com.ajax.model.Sala;
import br.com.ajax.repository.CursoRepository;
import br.com.ajax.service.CadastroSalaService;
import br.com.ajax.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroSalaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CursoRepository cursoRepository;

	@Inject
	private CadastroSalaService cadastroSalaService;

	private Sala sala;

	private List<Curso> cursos;

	public CadastroSalaBean() {
		limpar();
	}

	public void inicializar() {
		if (FacesUtil.isNotPostback()) {
			this.cursos = this.cursoRepository.cursos();
		}
	}

	public void salvar() {
		this.cadastroSalaService.salvar(this.sala);
		limpar();
		FacesUtil.addInfoMessage("Sala cadastrada com sucesso.");
	}

	public void limpar() {
		sala = new Sala();
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public List<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}

	public boolean isEditando() {
		return this.sala.getId() != null;
	}

}
