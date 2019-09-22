package br.esig.selecao.anotacao.repositorio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.esig.selecao.anotacao.dominio.Nota;

public class NotaRepositorio {
	private static List<Nota> notas;
	
	/// Métodos auxiliares
	
	private static int getId() {
		int maiorId = 0;
		
		if(!notas.isEmpty()) {
			for(Nota nota: notas) {
				if(nota.getId() >= maiorId) {
					maiorId = nota.getId();
				}
			}
		}
		
		return maiorId+1;
	}
	
	private static Date getDataHoje() {
		LocalDate agora = LocalDate.now();
		Date hoje = java.sql.Date.valueOf(agora);
		return hoje;
	}
	
	// Métodos do CRUD
	
	public static void salvar(Nota nota) { // (C)R(U)D
		// Inicializando o vetor
		if(notas == null) {
			notas = new ArrayList<Nota>();
			nota.setId(getId());
			nota.setDataCadastro(getDataHoje());
			notas.add(nota);
		}
		else {
			// Bifurcação de Create e Update
			// Não tem Id, então foi criado agora
			if(nota.getId() == null) {
				nota.setId(getId());
				nota.setDataCadastro(getDataHoje());
				notas.add(nota);
			} 
			else { // Tem id, então tá editando a nota
				nota.setDataUltimaEdicao(getDataHoje());
				notas.set(notas.indexOf(nota), nota);
			}
		}
		
	}
	
	public static Nota recuperar(Nota nota) { // C(R)UD
		if(notas.contains(nota)) {
			return notas.get(notas.indexOf(nota));
		}
		return null;
	}
	
	public static void remover(Nota nota) { // CRU(D)
		notas.remove(nota);
	}
	
	public static List<Nota> listarNotas(){ // C(R)UD
		return notas;
	}
	
	public static void limparNotas() {
		NotaRepositorio.notas.clear();
	}
}
