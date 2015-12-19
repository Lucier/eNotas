package br.com.ajax.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.ajax.model.Sala;
import br.com.ajax.repository.SalaRepository;
import br.com.ajax.util.jpa.Transactional;

public class CadastroSalaService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private SalaRepository salaRepositry;

	@Transactional
	public Sala salvar(Sala sala) {
		Sala salaExistente = salaRepositry.buscarPorNome(sala.getNome());

		if (salaExistente != null && !salaExistente.equals(sala)) {
			throw new NegocioException("Já existe uma sala cadastrada com o nome informado.");
		}

		return salaRepositry.salvar(sala);
	}

}
