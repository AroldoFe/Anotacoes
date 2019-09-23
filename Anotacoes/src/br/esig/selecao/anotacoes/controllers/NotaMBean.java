package br.esig.selecao.anotacoes.controllers;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Named;

import br.esig.selecao.anotacoes.dominio.Nota;
import br.esig.selecao.anotacoes.repositorio.NotaRepositorio;;


/**
 * ManageBean de Notas
 * @author aroldo-felix
 *
 */
@Named("notaMBean")
@SessionScoped
public class NotaMBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Nota nota;
	private DataModel<Nota> notasModel;
	
	public NotaMBean() {
		nota = new Nota();
	}
	/**
	 * Cadastrar uma nova nota
	 * @return formulário para cadastrar a nota
	 */
	public String novaNota() { 
		setNota(new Nota());
		return "/pages/form.jsf";
	}
	
	/**
	 * Listar todas as notas
	 * @return página com todas as notas listadas
	 */
	public String listarNotas() { // C(R)UD
		setNotasModel(new ListDataModel<Nota>(NotaRepositorio.listarNotas()));
		return "/pages/list.jsf";
	}
	
	/**
	 * Persistir uma nota (Criar ou Atualizar)
	 * @return formulário para cadastro de uma nova nota
	 */
	public String cadastrarNota() { // (C)RUD
		NotaRepositorio.salvar(nota);
		return novaNota();
	}
	
	/**
	 * Remover uma nota
	 * @return página com todas as notas listadas
	 */
	public String removerNota() { // CRU(D)
		Nota notaRemovida = getNotasModel().getRowData();
		NotaRepositorio.remover(notaRemovida);
		return listarNotas();
	}
	
	/**
	 * Editar uma nota
	 * @return Formulário preenchido com as informações da nota que se deseja editar
	 */
	public String editarNota() { // CR(U)D
		setNota(getNotasModel().getRowData());
		
		return "/pages/form.jsf";
	}

	// Métodos Getters e Setters
	
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
