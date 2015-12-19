package br.com.ajax.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.ajax.model.Aluno;
import br.com.ajax.repository.AlunoRepository;
import br.com.ajax.util.jpa.Transactional;

public class CadastroAlunoService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private AlunoRepository alunoRepository;

	@Transactional
	public Aluno salvar(Aluno aluno) {
		Aluno alunoMatriculaExistente = alunoRepository.buscarPorMatricula(aluno.getMatricula());

		if (alunoMatriculaExistente != null && !alunoMatriculaExistente.equals(aluno)) {
			throw new NegocioException("Já existe um aluno cadastrado com a matricula informada.");
		}

		return alunoRepository.salvar(aluno);
	}

}
