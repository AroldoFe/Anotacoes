package br.esig.selecao.anotacoes.repositorio;

import java.util.ArrayList;
import java.util.List;

import br.esig.selecao.anotacoes.dominio.Nota;

public class NotaRepositorio {
	private static List<Nota> notas;
	
	public static void adicionar(Nota nota) {
		if(notas == null) {
			notas = new ArrayList<Nota>();
		}
		notas.add(nota);
	}
	
	public static void remover(Nota nota) {
		notas.remove(nota);
	}
	
	public static List<Nota> listarNotas(){
		return notas;
	}
}
