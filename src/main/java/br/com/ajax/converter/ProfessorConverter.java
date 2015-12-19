package br.com.ajax.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.ajax.model.Professor;
import br.com.ajax.repository.ProfessorRepository;
import br.com.ajax.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Professor.class)
public class ProfessorConverter implements Converter {

	private ProfessorRepository professorRepository;

	public ProfessorConverter() {
		professorRepository = CDIServiceLocator.getBean(ProfessorRepository.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Professor retorno = null;

		if (value != null) {
			Long id = new Long(value);
			retorno = professorRepository.buscarPorId(id);
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Professor professor = (Professor) value;
			return professor.getId() == null ? null : professor.getId().toString();
		}

		return "";
	}

}
