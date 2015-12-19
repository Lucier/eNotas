package br.com.ajax.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.ajax.model.Professor;
import br.com.ajax.repository.filter.ProfessorFilter;
import br.com.ajax.service.NegocioException;
import br.com.ajax.util.jpa.Transactional;

public class ProfessorRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Professor buscarPorId(Long id) {
		return this.manager.find(Professor.class, id);
	}

	public List<Professor> professores() {
		return this.manager.createQuery("from Professor", Professor.class).getResultList();
	}

	public Professor salvar(Professor professor) {
		return this.manager.merge(professor);
	}

	@Transactional
	public void remover(Professor professor) {
		try {
			professor = buscarPorId(professor.getId());
			manager.remove(professor);
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("O professor selecionado não pode ser excluído");
		}
	}

	public Professor buscarPorNome(String nome) {
		try {
			return manager.createQuery("from Professor where  upper(nome) = :nome", Professor.class)
					.setParameter("nome", nome.toUpperCase()).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public Professor buscarPorLogin(String login) {
		try {
			return manager.createQuery("from Professor where  upper(login) = :login", Professor.class)
					.setParameter("login", login.toUpperCase()).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public Professor buscarPorMatricula(String matricula) {
		try {
			return manager.createQuery("from Professor where upper(matricula) = :matricula", Professor.class)
					.setParameter("matricula", matricula.toUpperCase()).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Professor> professoresFiltrados(ProfessorFilter professorFilter) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Professor.class);
		if (StringUtils.isNotBlank(professorFilter.getMatricula())) {
			criteria.add(Restrictions.ilike("matricula", professorFilter.getMatricula(), MatchMode.ANYWHERE));
		}

		return criteria.addOrder(Order.asc("matricula")).list();
	}

}
