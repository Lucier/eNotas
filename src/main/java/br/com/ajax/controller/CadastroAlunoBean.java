package br.com.ajax.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ajax.model.Aluno;
import br.com.ajax.model.Curso;
import br.com.ajax.model.Sala;
import br.com.ajax.repository.CursoRepository;
import br.com.ajax.repository.SalaRepository;
import br.com.ajax.service.CadastroAlunoService;
import br.com.ajax.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroAlunoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CadastroAlunoService cadastroAlunoService;

	@Inject
	private CursoRepository cursoRepository;

	@Inject
	private SalaRepository salaRepository;

	private Aluno aluno;

	private List<Curso> cursos;
	private List<Sala> salas;

	public CadastroAlunoBean() {
		limpar();
	}

	public void inicializar() {
		if (FacesUtil.isNotPostback()) {
			this.cursos = cursoRepository.cursos();
			this.salas = salaRepository.salas();
		}
	}

	public void salvar() {
		this.cadastroAlunoService.salvar(aluno);
		limpar();
		FacesUtil.addInfoMessage("Aluno cadastrado com sucesso.");
	}

	public void limpar() {
		aluno = new Aluno();
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public List<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}

	public List<Sala> getSalas() {
		return salas;
	}

	public void setSalas(List<Sala> salas) {
		this.salas = salas;
	}

	public boolean isEditando() {
		return this.aluno.getId() != null;
	}

}
