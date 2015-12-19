package br.com.ajax.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ajax.model.Curso;
import br.com.ajax.model.Disciplina;
import br.com.ajax.model.Professor;
import br.com.ajax.repository.CursoRepository;
import br.com.ajax.repository.ProfessorRepository;
import br.com.ajax.service.CadastroDisciplinaService;
import br.com.ajax.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroDisciplinaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CursoRepository cursoRepository;

	@Inject
	private ProfessorRepository professorRepository;

	@Inject
	private CadastroDisciplinaService cadastroDisciplinaService;

	private Disciplina disciplina;

	private List<Curso> cursos;
	private List<Professor> professores;

	public CadastroDisciplinaBean() {
		limpar();
	}

	public void inicializar() {
		if (FacesUtil.isNotPostback()) {
			this.cursos = cursoRepository.cursos();
			this.professores = professorRepository.professores();
		}
	}

	public void salvar() {
		this.cadastroDisciplinaService.salvar(disciplina);
		limpar();

		FacesUtil.addInfoMessage("Disciplina cadastrada com sucesso.");
	}

	public void limpar() {
		disciplina = new Disciplina();
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public List<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}

	public List<Professor> getProfessores() {
		return professores;
	}

	public void setProfessores(List<Professor> professores) {
		this.professores = professores;
	}

	public boolean isEditando() {
		return this.disciplina.getId() != null;
	}

}
