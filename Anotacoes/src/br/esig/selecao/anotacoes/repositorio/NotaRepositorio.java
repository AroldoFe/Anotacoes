package br.esig.selecao.anotacoes.repositorio;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.esig.selecao.anotacoes.dominio.Nota;

public class NotaRepositorio {
	
	private static Date getTodaysDate() {
		LocalDate now = LocalDate.now();
		Date today = java.sql.Date.valueOf(now);
		return today;
	}
	
	private static int getLastId() {
		List<Nota> notas = listarNotas();
		if(!notas.isEmpty()) {
			return notas.get(notas.size()-1).getId() + 1;
		}else {
			return 0;
		}
	}
	
	public static void adicionar(Nota nota) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("Anotacoes");
		EntityManager entityManager = factory.createEntityManager();
		
		entityManager.getTransaction().begin();
		
		//Nota notaBD = entityManager.find(Nota.class, nota.getId());
		Nota notaBD = null;
		
		if(notaBD == null) { // Is not in database
			nota.setId(getLastId());
			nota.setDataCadastro(getTodaysDate());	
			entityManager.persist(nota);
		}
//		} else {
//			nota.setDataUltimaEdicao(getTodaysDate());
//			entityManager.merge(nota);
//		}
		
		entityManager.getTransaction().commit();
		entityManager.close();
		factory.close();
	}
	
	public static void remover(Nota nota) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("Anotacoes");
		EntityManager entityManager = factory.createEntityManager();
		
		entityManager.getTransaction().begin();
		
		Nota removerNota = entityManager.find(Nota.class, nota.getId());
		if(removerNota != null) {
			entityManager.remove(removerNota);
		}
		entityManager.getTransaction().commit();
		
		entityManager.close();
		factory.close();
	}
	
	public static Nota procurar(Nota nota) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("Anotacoes");
		EntityManager entityManager = factory.createEntityManager();
		
		Nota notaBD = entityManager.find(Nota.class, nota.getId());
		
		entityManager.close();
		factory.close();
		
		return notaBD;
	}
	
	@SuppressWarnings("unchecked")
	public static List<Nota> listarNotas(){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("Anotacoes");
		EntityManager entityManager = factory.createEntityManager();
		
		List<Nota> notas = entityManager.createQuery("FROM " + Nota.class.getName()).getResultList();
		
		entityManager.close();
		factory.close();
		
		return notas; 
	}
}
