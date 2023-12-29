package edu.alexey.junit.homeworks.third.tdd;

public class User {

	private String name;
	private String password;
	private boolean isAdmin;
	private boolean isAuthenticated;

	public User(String name, String password, boolean isAdmin) {
		ensureParamValidity(name, password);
		this.name = name;
		this.password = password;
		this.isAdmin = isAdmin;
		this.isAuthenticated = false;
	}

	public User(String name, String password) {
		this(name, password, false);
	}

	public String getName() {
		return name;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	// свойство только для чтения
	public boolean isAuthenticated() {
		return isAuthenticated;
	}

	//3.6. метод аутентификации (делегирован классу User согласно заданию)
	public boolean authenticate(String name, String password) {
		ensureParamValidity(name, password);
		this.isAuthenticated = this.getName().equals(name) && this.password.equals(password);
		return this.isAuthenticated;
	}

	public void logout() {
		this.isAuthenticated = false;
	}

	private static void ensureParamValidity(String name, String password) {
		if (name == null) {
			throw new NullPointerException("name");
		}
		if (name.isBlank()) {
			throw new IllegalArgumentException("name");
		}
		if (password == null) {
			throw new NullPointerException("password");
		}
		if (password.isBlank()) {
			throw new IllegalArgumentException("password");
		}
	}
}