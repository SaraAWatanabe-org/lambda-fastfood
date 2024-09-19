package br.com.fiap.fastfood.models.dto;

public class LoginParamsDto {

	private String username;
	private String password;

	public LoginParamsDto() {
		super();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
