@database
Feature: opencart


  @db123
  Scenario Outline: Validate created opencart application user details in database
    Given user login into the opencart application
    When user navigates dashboard screen and User logs into the application with "<email>" and "<password>"
    Then user clicks on edit account
    Then user validates stores the account details like first name,last name and email
    Then user validates the user account details in the DB:"<tablename>"
    Examples:
      | tablename   | email                | password |
      | oc_customer | goutamgowda95@gmail.com | 1234     |




