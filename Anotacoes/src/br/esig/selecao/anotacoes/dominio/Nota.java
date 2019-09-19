package br.esig.selecao.anotacoes.dominio;

import java.util.Date;

public class Nota {

	private int id;
	private String titulo;
	private String descricao;
	private Date dataCadastro;
	private Usuario usuarioCadastro;
	private Date dataUltimaEdicao;
	private Usuario usuarioEditor;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Date getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public Usuario getUsuarioCadastro() {
		return usuarioCadastro;
	}
	public void setUsuarioCadastro(Usuario usuarioCadastro) {
		this.usuarioCadastro = usuarioCadastro;
	}
	
	public Date getDataUltimaEdicao() {
		return dataUltimaEdicao;
	}
	public void setDataUltimaEdicao(Date dataUltimaEdicao) {
		this.dataUltimaEdicao = dataUltimaEdicao;
	}
	public Usuario getUsuarioEditor() {
		return usuarioEditor;
	}
	public void setUsuarioEditor(Usuario usuarioEditor) {
		this.usuarioEditor = usuarioEditor;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Nota other = (Nota) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
