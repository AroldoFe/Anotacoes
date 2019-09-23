package br.esig.selecao.anotacoes.repositorio;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.esig.selecao.anotacoes.dominio.Nota;
/**
 * Classe que trata a persistência no banco
 * @author aroldo-felix
 */
public class NotaRepositorio {
	private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("Anotacoes");
	private static EntityManager entityManager = factory.createEntityManager();
	
	public NotaRepositorio() {
		if(factory == null) {
			factory = Persistence.createEntityManagerFactory("Anotacoes");
			entityManager = factory.createEntityManager();
		}
	}
	/**
	 * Método para pegar a data atual ao criar ou editar uma nota
	 * @return Data atual
	 */
	private static Date getTodaysDate() {
		LocalDate now = LocalDate.now();
		Date today = java.sql.Date.valueOf(now);
		return today;
	}
	
	/**
	 * Método para auxiliar nas Id's das notas
	 * @return id para guardar na nota
	 */
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
	
	/**
	 * Método que vai decidir se adicionar ou só atualiza a nota no banco
	 * @param nota que deverá ser persistida
	 */
	public static void salvar(Nota nota) {
		if(nota.getId() == null) {
			adicionar(nota);
		} else {
			atualizar(nota);
		}
	}
	
	/**
	 * Método para adicionar uma nota no banco
	 * @param nota que deverá ser adicionada
	 */
	public static void adicionar(Nota nota) {		
		entityManager.getTransaction().begin();
		
		nota.setId(getLastId());
		nota.setDataCadastro(getTodaysDate());	
		entityManager.persist(nota);
		
		entityManager.getTransaction().commit();
	}
	
	/**
	 * Método para atualizar uma nota que já existe
	 * @param nota
	 */
	public static void atualizar(Nota nota) {
		entityManager.getTransaction().begin();
		
		nota.setDataUltimaEdicao(getTodaysDate()); 
		entityManager.merge(nota); 
		
		entityManager.getTransaction().commit();
	}
	
	/**
	 * Método para remover uma nota do banco
	 * @param nota
	 */
	public static void remover(Nota nota) {
		entityManager.getTransaction().begin();
		
		Nota removerNota = entityManager.find(Nota.class, nota.getId());
		if(removerNota != null) {
			entityManager.remove(removerNota);
		}
		entityManager.getTransaction().commit();
	}
	
	/**
	 * Método para procurar uma nota
	 * @param nota para pesquisar no banco
	 * @return nota no banco
	 */
	public static Nota procurar(Nota nota) {
		Nota notaBD = entityManager.find(Nota.class, nota.getId());
		
		return notaBD;
	}
	
	/**
	 * Método para pegar todas as notas do banco
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Nota> listarNotas(){
		List<Nota> notas = entityManager.createQuery("FROM " + Nota.class.getName()).getResultList();
		
		return notas; 
	}
}
