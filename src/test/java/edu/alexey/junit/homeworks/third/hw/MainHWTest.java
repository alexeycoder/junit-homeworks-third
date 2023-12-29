package edu.alexey.junit.homeworks.third.hw;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MainHWTest {

	static final int SAMPLES = 1000;

	static final int LEFT_BOUND = 25;
	static final int RIGHT_BOUND = 100;

	static {
		assert RIGHT_BOUND > LEFT_BOUND;
	}

	private MainHW mainHw;

	@BeforeEach
	void setUp() {
		// Поскольку заданием предложен экземплярный метод, создаём экземпляр
		// для доступа к нему на каждом испытании:
		mainHw = new MainHW();
	}

	// evenOddNumber(int)

	@Test
	void evenOddNumberReturnsTrueForEvenInts() {

		generateEvenInts(SAMPLES).mapToObj(mainHw::evenOddNumber).forEach(Assertions::assertTrue);
	}

	@Test
	void evenOddNumberReturnsFalseForOddInts() {

		generateOddInts(SAMPLES).mapToObj(mainHw::evenOddNumber).forEach(Assertions::assertFalse);
	}

	@Test
	void evenOddNumberReturnsTrueForZero() {

		assertTrue(mainHw.evenOddNumber(0));
	}

	private static IntStream generateEvenInts(int count) {
		final int rawMin = Integer.MIN_VALUE / 2;
		final int rawMax = Integer.MAX_VALUE / 2;
		return ThreadLocalRandom.current()
				.ints(count, rawMin, rawMax)
				.map(v -> v << 1);
	}

	private static IntStream generateOddInts(int count) {
		return generateEvenInts(count).map(i -> i + 1);
	}

	// numberInInterval(int)

	@ParameterizedTest
	@ValueSource(ints = { LEFT_BOUND, LEFT_BOUND - 1, LEFT_BOUND - 100 })
	void numberInIntervalReturnsFalseForLeftBoundAndBelow(int n) {
		assertFalse(mainHw.numberInInterval(n));
	}

	@ParameterizedTest
	@ValueSource(ints = { RIGHT_BOUND, RIGHT_BOUND + 1, RIGHT_BOUND + 100 })
	void numberInIntervalReturnsFalseForRightBoundAndAbove(int n) {
		assertFalse(mainHw.numberInInterval(n));
	}

	@ParameterizedTest
	@ValueSource(ints = { LEFT_BOUND + 1, RIGHT_BOUND - 1, (LEFT_BOUND + RIGHT_BOUND) / 2 })
	void numberInIntervalReturnsTrueInBetweenBounds(int n) {
		assertTrue(mainHw.numberInInterval(n));
	}
}
