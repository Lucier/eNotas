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

import br.com.ajax.model.Aluno;
import br.com.ajax.repository.filter.AlunoFilter;
import br.com.ajax.service.NegocioException;
import br.com.ajax.util.jpa.Transactional;

public class AlunoRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Aluno buscarPorId(Long id) {
		return this.manager.find(Aluno.class, id);
	}

	public List<Aluno> alunos() {
		return this.manager.createQuery("from Aluno", Aluno.class).getResultList();
	}

	public Aluno salvar(Aluno aluno) {
		return this.manager.merge(aluno);
	}

	@Transactional
	public void remover(Aluno aluno) {
		try {
			aluno = buscarPorId(aluno.getId());
			manager.remove(aluno);
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("O aluno selecionado não pode ser excluído");
		}
	}

	public Aluno buscarPorNome(String nome) {
		try {
			return manager.createQuery("from Aluno where  upper(nome) = :nome", Aluno.class)
					.setParameter("nome", nome.toUpperCase()).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public Aluno buscarPorMatricula(String matricula) {
		try {
			return manager.createQuery("from Aluno where upper(matricula) = :matricula", Aluno.class)
					.setParameter("matricula", matricula.toUpperCase()).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Aluno> alunosFiltrados(AlunoFilter alunoFilter) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Aluno.class);
		if (StringUtils.isNotBlank(alunoFilter.getMatricula())) {
			criteria.add(Restrictions.ilike("matricula", alunoFilter.getMatricula(), MatchMode.ANYWHERE));
		}

		return criteria.addOrder(Order.asc("matricula")).list();
	}

}
