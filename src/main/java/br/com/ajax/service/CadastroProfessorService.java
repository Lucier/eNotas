package br.com.ajax.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.ajax.model.Professor;
import br.com.ajax.repository.ProfessorRepository;
import br.com.ajax.util.jpa.Transactional;

public class CadastroProfessorService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ProfessorRepository professorRepository;

	@Transactional
	public Professor salvar(Professor professor) {
		Professor professorMatriculaExistente = professorRepository.buscarPorMatricula(professor.getMatricula());

		if (professorMatriculaExistente != null && !professorMatriculaExistente.equals(professor)) {
			throw new NegocioException("Já existe um professor cadastrado com a matricula informada.");
		}

		return professorRepository.salvar(professor);
	}

}
