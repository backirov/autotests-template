# smpl-at

## Требования

- Java 17
- Maven 3.9+
- Google Chrome

## Профили запуска

В проекте доступны Maven profiles:

- `local`
- `qa`


## Запуск тестов

Запуск всех тестов с профилем по умолчанию:

```bash
mvn test
```

Запуск всех тестов с профилем `local`:

```bash
mvn test -Plocal
```

Запуск всех тестов с профилем `qa`:

```bash
mvn test -Pqa
```

Запуск одного тестового класса:

```bash
mvn test -Dtest=SampleTest
```

Запуск одного тестового класса с профилем:

```bash
mvn test -Pqa -Dtest=SampleTest
```

Переопределение отдельных параметров из командной строки:

```bash
mvn test -Pqa -Dbrowser=chrome -Dheadless=true
```

## Сборка проекта

Сборка без запуска тестов:

```bash
mvn clean package -DskipTests
```

Полная сборка с запуском тестов:

```bash
mvn clean test
```

Полная сборка с профилем:

```bash
mvn clean test -Pqa
```

## Allure

Во время запуска тестов результаты Allure складываются в:

```text
target/allure-results
```

Генерация HTML-отчета:

```bash
mvn allure:report
```

Генерация HTML-отчета после запуска тестов:

```bash
mvn test -Pqa
mvn allure:report
```

Просмотр отчета через временный локальный сервер:

```bash
mvn allure:serve
```

Запуск тестов и последующий просмотр отчета:

```bash
mvn test -Pqa
mvn allure:serve
```

Сгенерированный HTML-отчет обычно находится в:

```text
target/site/allure-maven-plugin
```

## Выбор конфигурации

Файл конфигурации выбирается по значению `test.profile`:

- `local` -> `src/test/resources/local.properties`
- `qa` -> `src/test/resources/qa.properties`

При запуске тестов в консоль выводятся:

- выбранный профиль
- выбранный property-файл