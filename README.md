## Unit-тесты 3. Качество тестов

### Задание 1

Напишите тесты, покрывающие на 100% метод evenOddNumber, который проверяет, является ли переданное число четным или нечетным. (код приложен в презентации)

### Задание 2

Разработайте и протестируйте метод numberInInterval, который проверяет, попадает ли переданное число в интервал (25;100). (код приложен в презентации)

### Задание 3

Добавьте функцию в класс UserRepository, которая разлогинивает всех пользователей, кроме администраторов. Для этого, вам потребуется расширить класс User новым свойством, указывающим, обладает ли пользователь админскими правами. Протестируйте данную функцию.

### Решение

__1. и 2.__

Класс с методами *evenOddNumber(int)* и *numberInInterval(int)*&nbsp;&mdash; [class MainHW](src/main/java/edu/alexey/junit/homeworks/third/hw/MainHW.java)

Класс с тестами методов *evenOddNumber(int)* и *numberInInterval(int)*&nbsp;&mdash; [class MainHWTest](src/test/java/edu/alexey/junit/homeworks/third/hw/MainHWTest.java)

__3.__

Метод разлогинивания *logoutAllButAdmin()*&nbsp;&mdash; в классе репозитория [class UserRepository](src/main/java/edu/alexey/junit/homeworks/third/tdd/UserRepository.java)

Тесты метода *logoutAllButAdmin()*&nbsp;&mdash; в классе [class UserRepositoryTest](src/test/java/edu/alexey/junit/homeworks/third/tdd/UserRepositoryTest.java)

__Показатели покрытия тестами методов `evenOddNumber(int)` и `numberInInterval(int)`:__

![Coverage](https://raw.githubusercontent.com/alexeycoder/illustrations/main/java-junit-hw3/test_coverage_example.png)

__Пример выполнения тестирования:__

	mvn clean test

![Tests](https://raw.githubusercontent.com/alexeycoder/illustrations/main/java-junit-hw3/tests_example.png)
