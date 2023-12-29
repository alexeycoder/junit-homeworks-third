package edu.alexey.junit.homeworks.third.tdd;

import static org.assertj.core.api.Assertions.assertThatCollection;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserRepositoryTest {

	static final String COMMON_USER_NAME = "User";
	static final String ADMIN_NAME = "Admin";
	static final String PASSWORD = "password";

	/**
	 * Генерирует коллекцию из заданного количества аутентифицированных обычных
	 * пользователей и аутентифицированных администраторов в случайном порядке.
	 * 
	 * @param commonCount Количество обычных (не администраторов) пользователей.
	 * @param adminsCount Количество администраторов.
	 * @return Результирующая коллекция.
	 */
	private static List<User> generateAuthenticatedUsers(int commonCount, int adminsCount) {

		var users = IntStream.rangeClosed(1, commonCount)
				.mapToObj(i -> {
					User u = new User(COMMON_USER_NAME, PASSWORD);
					u.authenticate(COMMON_USER_NAME, PASSWORD);
					return u;
				});

		var admins = IntStream.rangeClosed(1, adminsCount)
				.mapToObj(i -> {
					User u = new User(ADMIN_NAME, PASSWORD, true);
					u.authenticate(ADMIN_NAME, PASSWORD);
					return u;
				});

		var allThem = Stream.concat(users, admins).collect(Collectors.toCollection(ArrayList::new));
		Collections.shuffle(allThem);

		// Проверим на всякий случай что тестовый набор генерируется корректно:
		assert allThem.size() == commonCount + adminsCount;
		assert allThem.stream().filter(User::isAdmin).count() == adminsCount;
		return allThem;
	}

	private UserRepository userRepository;

	@BeforeEach
	void setUp() {
		userRepository = new UserRepository();
	}

	@Test
	void logoutAllButAdminCausesNoErrorForEmptyRepo() {
		assertThatCollection(userRepository.authenticatedUsersView()).isEmpty();
		assertDoesNotThrow(userRepository::logoutAllButAdmin);
		assertThatCollection(userRepository.authenticatedUsersView()).isEmpty();
	}

	@Test
	void logoutAllButAdminEmpiesOnlyRepoOfCommonUsers() {
		final int n = 10;
		generateAuthenticatedUsers(n, 0).forEach(userRepository::addUser);

		assertThatCollection(userRepository.authenticatedUsersView()).hasSize(n);
		assertDoesNotThrow(userRepository::logoutAllButAdmin);
		assertThatCollection(userRepository.authenticatedUsersView()).isEmpty();
	}

	@Test
	void logoutAllButAdminDoesNotAffectRepoOfOnlyAdmins() {
		final int n = 10;
		generateAuthenticatedUsers(0, n).forEach(userRepository::addUser);

		assertThatCollection(userRepository.authenticatedUsersView()).hasSize(n);
		assertDoesNotThrow(userRepository::logoutAllButAdmin);
		assertThatCollection(userRepository.authenticatedUsersView()).hasSize(n);
	}

	@Test
	void logoutAllButAdminRemovesCommonAndLeavesAdmins() {
		final int nCommon = 23;
		final int nAdmins = 12;
		generateAuthenticatedUsers(nCommon, nAdmins).forEach(userRepository::addUser);

		assertThatCollection(userRepository.authenticatedUsersView()).hasSize(nCommon + nAdmins);
		assertTrue(userRepository.findByName(COMMON_USER_NAME));
		assertTrue(userRepository.findByName(ADMIN_NAME));

		assertDoesNotThrow(userRepository::logoutAllButAdmin);

		assertFalse(userRepository.findByName(COMMON_USER_NAME));
		assertThatCollection(userRepository.authenticatedUsersView())
				.hasSize(nAdmins)
				.allMatch(User::isAdmin);

	}
}
