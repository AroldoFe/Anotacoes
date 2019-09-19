package br.esig.selecao.anotacoes.controllers;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.enterprise.context.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.inject.Named;
import javax.swing.text.DateFormatter;

import br.esig.selecao.anotacoes.dominio.Nota;
import br.esig.selecao.anotacoes.repositorio.NotaRepositorio;;



@Named("notaMBean")
@SessionScoped
public class NotaMBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Nota nota;
	private DataModel<Nota> notasModel;
	
	@Inject
	private UsuarioMBean usuarioMBean;
	
	public NotaMBean() {
		nota = new Nota();
	}
	
	public String novaNota() { 
		nota = new Nota();
		return "/pages/material/form.jsf";
	}
	
	public String listarNotas() { // C(R)UD
		notasModel = new ListDataModel<Nota>(NotaRepositorio.listarNotas());
		return "/pages/material/list.jsf";
	}
	
	public String cadastrarNota() { // (C)RUD
		nota.setUsuarioCadastro(usuarioMBean.getUsuarioLogado());
		nota.setDataCadastro(getTodaysDate());
		NotaRepositorio.adicionar(nota);
		nota = new Nota();
		return "/pages/material/form.jsf";
	}
	
	public String removerNota() { // CRU(D)
		Nota notaRemovida = notasModel.getRowData();
		NotaRepositorio.remover(notaRemovida);
		return listarNotas();
	}
	
	private Date getTodaysDate() {
		LocalDate now = LocalDate.now();
		Date today = java.sql.Date.valueOf(now);
		return today;
	}
	
	public String editarNota() { // CR(U)D
		nota = notasModel.getRowData();
		nota.setUsuarioEditor(usuarioMBean.getUsuarioLogado());
		
		nota.setDataUltimaEdicao(getTodaysDate());
		return "/pages/material/form.jsf";
	}

	public Nota getMaterial() {
		return nota;
	}

	public void setMaterial(Nota nota) {
		this.nota = nota;
	}

	public DataModel<Nota> getMateriaisModel() {
		return notasModel;
	}

	public void setNotasModel(DataModel<Nota> notasModel) {
		this.notasModel = notasModel;
	}

	public UsuarioMBean getUsuarioMBean() {
		return usuarioMBean;
	}

	public void setUsuarioMBean(UsuarioMBean usuarioMBean) {
		this.usuarioMBean = usuarioMBean;
	}
}
