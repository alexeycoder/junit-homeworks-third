package edu.alexey.junit.homeworks.third.tdd;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {

	// Тут можно хранить аутентифицированных пользователей
	final List<User> authenticatedUsers = new ArrayList<>();

	public List<User> authenticatedUsersView() {
		return List.copyOf(authenticatedUsers);
	}

	/**
	 * Метод служит для добавления аутентифицированных пользователей в
	 * соответствующий репозиторий. Не допустимо использовать для пользователей, не
	 * прошедших успешно аутентификацию.
	 * 
	 * @param user Аутентифицированный пользователь.
	 * @throws IllegalArgumentException если добавляемый пользователь не прошёл
	 *                                  успешно аутентификацию.
	 */
	public void addUser(User user) {
		if (user == null) {
			throw new NullPointerException("user");
		}
		if (!user.isAuthenticated()) {
			throw new IllegalArgumentException("user");
		}
		authenticatedUsers.add(user);
	}

	/**
	 * Разлогинивает всех пользователей, кроме администраторов.
	 */
	public void logoutAllButAdmin() {

		// Выводим не-админов из репозитория аутентифицированных и сразу же
		// меняем их статус:
		authenticatedUsers.removeIf(u -> {
			if (u.isAdmin()) {
				return false;
			} else {
				u.logout();
				return true;
			}
		});
	}

	public boolean findByName(String username) {
		if (username == null) {
			throw new NullPointerException("username");
		}
		for (User user : authenticatedUsers) {
			if (user.getName().equals(username)) {
				return true;
			}
		}
		return false;
	}
}