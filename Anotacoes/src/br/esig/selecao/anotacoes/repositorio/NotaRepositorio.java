package br.esig.selecao.anotacoes.repositorio;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.esig.selecao.anotacoes.dominio.Nota;

public class NotaRepositorio {
	private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("Anotacoes");
	private static EntityManager entityManager = factory.createEntityManager();
	
	public NotaRepositorio() {
		if(factory == null) {
			factory = Persistence.createEntityManagerFactory("Anotacoes");
			entityManager = factory.createEntityManager();
		}
	}
	
	private static Date getTodaysDate() {
		LocalDate now = LocalDate.now();
		Date today = java.sql.Date.valueOf(now);
		return today;
	}
	
	private static int getLastId() {
		List<Nota> notas = listarNotas();
		int maiorId = 0;
		
		for(Nota nota: notas) {
			if(maiorId <= nota.getId()) {
				maiorId = nota.getId()+1;
			}
		}
		return maiorId;
	}
	
	public static void salvar(Nota nota) {
		if(nota.getId() == null) {
			adicionar(nota);
		} else {
			atualizar(nota);
		}
	}
	
	public static void adicionar(Nota nota) {		
		entityManager.getTransaction().begin();
		
		nota.setId(getLastId());
		nota.setDataCadastro(getTodaysDate());	
		entityManager.persist(nota);
		
		entityManager.getTransaction().commit();
	}
	
	public static void atualizar(Nota nota) {
		entityManager.getTransaction().begin();
		
		nota.setDataUltimaEdicao(getTodaysDate()); 
		entityManager.merge(nota); 
		
		entityManager.getTransaction().commit();
	}
	
	public static void remover(Nota nota) {
		entityManager.getTransaction().begin();
		
		Nota removerNota = entityManager.find(Nota.class, nota.getId());
		if(removerNota != null) {
			entityManager.remove(removerNota);
		}
		entityManager.getTransaction().commit();
	}
	
	public static Nota procurar(Nota nota) {
		Nota notaBD = entityManager.find(Nota.class, nota.getId());
		
		return notaBD;
	}
	
	@SuppressWarnings("unchecked")
	public static List<Nota> listarNotas(){
		List<Nota> notas = entityManager.createQuery("FROM " + Nota.class.getName()).getResultList();
		
		return notas; 
	}
}
