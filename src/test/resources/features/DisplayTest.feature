@all
Feature: DisplayTest

  Scenario: Checking the presence of the Contacts block in Header
    Given I am on the main page
    Then Contacts block displayed

  Scenario: Checking the presence and display of a block with Certificates.
    Given I am on the main page
    Then Certificates block should be displayed

  Scenario: Checking the presence and displaying the Footer.
    Given I am on the main page
    Then Footer should be displayed