package edu.alexey.junit.homeworks.third.hw;

public class MainHW {

	// HW 3.1. Нужно покрыть тестами метод на 100%
	// Метод проверяет, является ли целое число записанное в переменную n, чётным (true) либо нечётным (false).

	// Реализация метода -- из презентации
	public boolean evenOddNumber(int n) {
		if (n % 2 == 0) {
			return true;
		} else {
			return false;
		}
	}

	// HW 3.2. Нужно написать метод который проверяет, попадает ли переданное число в интервал (25;100) и возвращает true, если попадает и false - если нет,
	// покрыть тестами метод на 100%

	static final int LEFT_BOUND = 25;
	static final int RIGHT_BOUND = 100;

	public boolean numberInInterval(int n) {
		return n > LEFT_BOUND && n < RIGHT_BOUND;
	}
}
