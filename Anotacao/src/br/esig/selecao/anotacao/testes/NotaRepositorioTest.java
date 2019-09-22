package br.esig.selecao.anotacao.testes;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.esig.selecao.anotacao.dominio.Nota;
import br.esig.selecao.anotacao.repositorio.NotaRepositorio;

public class NotaRepositorioTest {
	private Nota nota;
	
	@Before
	public void setUp() {
		nota = new Nota();
		nota.setTitulo("teste salvar");
		nota.setDescricao("descricao de um teste.");
		NotaRepositorio.salvar(nota);
	}
	
	// Testes para o método adicionar
	
	@Test
	public void testAdicionar() {
		Nota notaRepo = NotaRepositorio.recuperar(nota);
		
		// Se foi inserido, tem o mesmo id, mesmo titulo, mesmo descricao e mesma data de cadastro
		assertTrue((nota.getId().equals(notaRepo.getId())) && 
					(nota.getTitulo().equals(notaRepo.getTitulo())) &&
					(nota.getDescricao().equals(notaRepo.getDescricao())) &&
					(nota.getDataCadastro().equals(notaRepo.getDataCadastro()))
					);
	}
	
	@Test
	public void testAtualizarTitulo() {
		// Após inserida, vamos alterar
		nota.setTitulo("titulo alterado");
		NotaRepositorio.salvar(nota);
		
		Nota notaRepo = NotaRepositorio.recuperar(nota);
		
		assertTrue((nota.getId().equals(notaRepo.getId())) && 
				(nota.getTitulo().equals(notaRepo.getTitulo())) &&
				(nota.getDescricao().equals(notaRepo.getDescricao())) &&
				(nota.getDataCadastro().equals(notaRepo.getDataCadastro())) &&
				(notaRepo.getDataUltimaEdicao() != null)
				);
	}
	
	@Test
	public void testAtualizarDescricao() {
		// Após inserida, vamos alterar
		nota.setDescricao("Teste após a edição.");
		NotaRepositorio.salvar(nota);
		
		Nota notaRepo = NotaRepositorio.recuperar(nota);
		
		assertTrue((nota.getId().equals(notaRepo.getId())) && 
				(nota.getTitulo().equals(notaRepo.getTitulo())) &&
				(nota.getDescricao().equals(notaRepo.getDescricao())) &&
				(nota.getDataCadastro().equals(notaRepo.getDataCadastro())) &&
				(notaRepo.getDataUltimaEdicao() != null)
				);
	}
	
	@Test
	public void testRemover() {
		NotaRepositorio.remover(nota);
		Nota notaRepo = NotaRepositorio.recuperar(nota);
		assertNull(notaRepo);
	}
	
	@Test
	public void testRecuperar() {
		Nota notaRepo = NotaRepositorio.recuperar(nota);
		assertEquals(nota, notaRepo);
	}
	
	@Test
	public void testListar() {
		List<Nota> notas = NotaRepositorio.listarNotas();
		assertNotNull(notas);
	}
	
	@After
	public void limpar()
	{
		NotaRepositorio.limparNotas();
	}

}
