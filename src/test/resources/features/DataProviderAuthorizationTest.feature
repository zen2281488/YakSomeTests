@all
Feature: DataProvider Auth Test

  Scenario Outline: Testing login with different credentials
    Given User is on the login page
    When User logs in with username "<username>", description "<description>" and password "<loginPassword>"
    Then Successful login message is displayed

    Examples:
      | username | loginPassword | description |
      | angular  | password      | angular     |
      | angular  | password1     | angular     |
      | angular1 | password      | angular     |
      | angular1 | password1     | angular     |
