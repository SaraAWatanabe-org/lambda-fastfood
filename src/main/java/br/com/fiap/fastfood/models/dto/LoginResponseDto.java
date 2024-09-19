package br.com.fiap.fastfood.models.dto;

public class LoginResponseDto {

	private int statusCode;
	private String idToken;
	private String accessToken;
	private String refreshToken;
	private String message;

	public LoginResponseDto() {
	}

	public LoginResponseDto(int statusCode, String idToken, String accessToken, String refreshToken, String message) {
		super();
		this.statusCode = statusCode;
		this.idToken = idToken;
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
		this.message = message;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getIdToken() {
		return idToken;
	}

	public void setIdToken(String idToken) {
		this.idToken = idToken;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
