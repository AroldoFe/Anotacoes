package br.esig.selecao.anotacoes.dominio;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "nota", schema = "public")
@Entity
public class Nota {

	@Id
	@Column//(name = "id", nullable = false)
	private int id;
	
	@Column//(name = "titulo", nullable = false)
	private String titulo;
	
	@Column//(name = "descricao", nullable = false)
	private String descricao;
	
	@Column//(name = "dataCadastro", nullable = false)
	private Date dataCadastro;
	
	@Column//(name = "dataUltimaEdicao", nullable = true)
	private Date dataUltimaEdicao;
	
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
	public Date getDataUltimaEdicao() {
		return dataUltimaEdicao;
	}
	public void setDataUltimaEdicao(Date dataUltimaEdicao) {
		this.dataUltimaEdicao = dataUltimaEdicao;
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
