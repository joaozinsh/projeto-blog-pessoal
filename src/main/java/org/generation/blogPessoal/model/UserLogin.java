package org.generation.blogPessoal.model;

import javax.validation.constraints.NotBlank;

public class UserLogin {

	private String nome;
	
	@NotBlank
	private String usuario;
	
	@NotBlank
	private String senha;
	
	private String token;

	public UserLogin(String nome, @NotBlank String usuario, @NotBlank String senha, String token) {
		this.nome = nome;
		this.usuario = usuario;
		this.senha = senha;
		this.token = token;
	}

	public UserLogin() {
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
