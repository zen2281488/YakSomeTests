Feature: DisplayTest

  Scenario: Проверка наличия блока Контактов в Хедере
    Given I am on the main page
    Then Contacts block displayed

  Scenario: Проверка наличия и отображения блока с Сертификатами.
    Given I am on the main page
    Then Certificates block should be displayed

  Scenario: Проверка наличия и отображение Футера.
    Given I am on the main page
    Then Footer should be displayed