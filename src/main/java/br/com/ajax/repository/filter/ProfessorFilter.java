package br.com.ajax.repository.filter;

import java.io.Serializable;

public class ProfessorFilter implements Serializable {

	private static final long serialVersionUID = 1L;

	private String matricula;

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

}
