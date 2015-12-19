package br.com.ajax.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.ajax.model.Disciplina;
import br.com.ajax.repository.DisciplinaRepository;
import br.com.ajax.util.jpa.Transactional;

public class CadastroDisciplinaService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private DisciplinaRepository disciplinaRepository;

	@Transactional
	public Disciplina salvar(Disciplina disciplina) {

		return disciplinaRepository.salvar(disciplina);
	}

}
