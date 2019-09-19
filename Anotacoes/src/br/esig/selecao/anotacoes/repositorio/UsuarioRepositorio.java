package br.esig.selecao.anotacoes.repositorio;

import java.util.ArrayList;
import java.util.List;

import br.esig.selecao.anotacoes.dominio.Usuario;

public class UsuarioRepositorio {
	public static List<Usuario> usuarios;
	
	public static Usuario getUsuario(String login, String senha) {
		if(usuarios == null) {
			usuarios = new ArrayList<Usuario>();
			
			Usuario usr = new Usuario("Aroldo", "Aroldo");
			usr.setNome(usr.getLogin().substring(0,1).toUpperCase().concat(usr.getLogin().substring(1)));
			usuarios.add(usr);
		}
		
		for(Usuario u : usuarios) {
			if(u.getLogin().equals(login) && u.getSenha().equals(senha)) {
				return u;
			}
		}
		
		return null;
	}
}
