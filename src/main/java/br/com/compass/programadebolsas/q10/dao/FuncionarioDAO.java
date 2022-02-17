package br.com.compass.programadebolsas.q10.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.compass.programadebolsas.q10.model.Funcionario;
import br.com.compass.programadebolsas.q10.util.JPAUtil;

public class FuncionarioDAO {
	
	EntityManager em;
	
	public FuncionarioDAO() {
		this.em=JPAUtil.getEntityManager();
	} 
	
	public void cadastrar(Funcionario func) {
		em.persist(func);
	}	
	
	public void trasactionBegin() {
		this.em.getTransaction().begin();
	}
	
	public void commit() {
		this.em.getTransaction().commit();
	}
	
	public void close() {
		this.em.close();
	}
	
	public List<Funcionario> listaTodosFuncionarios(){
		String jpql = "SELECT f FROM Funcionario AS f";
		return em.createQuery(jpql,Funcionario.class).getResultList();
	}
}
