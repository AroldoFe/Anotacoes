package br.esig.selecao.anotacao.controllers;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Named;

import br.esig.selecao.anotacao.dominio.Nota;
import br.esig.selecao.anotacao.repositorio.NotaRepositorio;

@Named("notaMBean")
@SessionScoped
public class NotaMBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Nota nota;
	private DataModel<Nota> dataModel;
	
	// Métodos de CRUD para navegação entre as páginas
	
	public String novaNota() {
		setNota(new Nota());
		return "/pages/form.jsf";
	}
	
	public String salvarNota() { // C
		NotaRepositorio.salvar(getNota());
		return novaNota();
	}
	
	public String listarNotas() { // R
		setDataModel(NotaRepositorio.listarNotas());
		return "/pages/list.jsf";
	}
	
	public String removerNota() {
		Nota notaRemovida = getDataModel().getRowData();
		NotaRepositorio.remover(notaRemovida);
		return listarNotas();
	}
	
	public String editarNota() {
		setNota(getDataModel().getRowData());
		return "/pages/form.jsf";
	}
	
	// Métodos Getters e Setters

	public NotaMBean() {
		setNota(new Nota());
	}
	
	public Nota getNota() {
		return nota;
	}
	
	public void setNota(Nota nota) {
		this.nota = nota;
	}
	
	public DataModel<Nota> getDataModel() {
		return dataModel;
	}
	
	public void setDataModel(List<Nota> dataModel) {
		this.dataModel = new ListDataModel<Nota>(dataModel);
	}
	
	

}
