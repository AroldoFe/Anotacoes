package br.esig.selecao.anotacoes.controllers;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Named;

import br.esig.selecao.anotacoes.dominio.Nota;
import br.esig.selecao.anotacoes.repositorio.NotaRepositorio;;



@Named("notaMBean")
@SessionScoped
public class NotaMBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Nota nota;
	private DataModel<Nota> notasModel;
	
	
	public NotaMBean() {
		nota = new Nota();
	}
	
	public String novaNota() { 
		nota = new Nota();
		return "/pages/form.jsf";
	}
	
	public String listarNotas() { // C(R)UD
		notasModel = new ListDataModel<Nota>(NotaRepositorio.listarNotas());
		return "/pages/list.jsf";
	}
	
	public String cadastrarNota() { // (C)RUD
		NotaRepositorio.adicionar(nota);
		return novaNota();
	}
	
	public String removerNota() { // CRU(D)
		Nota notaRemovida = notasModel.getRowData();
		NotaRepositorio.remover(notaRemovida);
		return listarNotas();
	}
	
	public String editarNota() { // CR(U)D
		nota = notasModel.getRowData();
		
		return "/pages/form.jsf";
	}

	public Nota getNota() {
		return nota;
	}

	public void setNota(Nota nota) {
		this.nota = nota;
	}

	public DataModel<Nota> getNotasModel() {
		return notasModel;
	}

	public void setNotasModel(DataModel<Nota> notasModel) {
		this.notasModel = notasModel;
	}
}
