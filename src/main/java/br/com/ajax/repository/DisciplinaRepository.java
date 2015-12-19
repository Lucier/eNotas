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

import br.com.ajax.model.Disciplina;
import br.com.ajax.repository.filter.DisciplinaFilter;
import br.com.ajax.service.NegocioException;
import br.com.ajax.util.jpa.Transactional;

public class DisciplinaRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Disciplina buscarPorid(Long id) {
		return this.manager.find(Disciplina.class, id);
	}

	public List<Disciplina> disciplinas() {
		return this.manager.createQuery("from Disciplina", Disciplina.class).getResultList();
	}

	public Disciplina salvar(Disciplina disciplina) {
		return this.manager.merge(disciplina);
	}

	@Transactional
	public void remover(Disciplina disciplina) {
		try {
			disciplina = buscarPorid(disciplina.getId());
			manager.remove(disciplina);
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("a disciplina selecionada não pode ser excluída.");
		}
	}

	public Disciplina buscarPorNome(String nome) {
		try {
			return this.manager.createQuery("from Disciplina where upper(nome) = :nome", Disciplina.class)
					.setParameter("nome", nome.toUpperCase()).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Disciplina> disciplinasFiltradas(DisciplinaFilter disciplinaFilter) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Disciplina.class);
		if (StringUtils.isNotBlank(disciplinaFilter.getNome())) {
			criteria.add(Restrictions.ilike("nome", disciplinaFilter.getNome(), MatchMode.ANYWHERE));
		}

		return criteria.addOrder(Order.asc("nome")).list();
	}

}
