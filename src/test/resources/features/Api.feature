@RestAPI
Feature: RestAPI

  @crud
  Scenario Outline:API_001 Validate employee creation CRUD operation
    Given User creates reference with RestAPI BaseURI
    When User creates the employee:"<jsonpath>"
    Then User Validates the Response Code and body of the created employee
    When User search for the created employee:"<jsonpath>"
    Then User Validates the Response Code and body of the searched employee
    When User updates the searched employee:"<jsonpath>"
    Then User Validates the Response Code and body of the updated employee
    When User delete the updated employee:"<jsonpath>"
    Then User Validates the Response Code and body of the deleted employee
    Examples:
      | TestcaseId         | jsonpath |
      | TC_TP_API_S0001_01 | Api      |


  @post
  Scenario Outline:API_002 Validate employee creation post operation
    Given User creates reference with RestAPI BaseURI
    When User creates the employee:"<jsonpath>"
    Then User Validates the Response Code and body of the created employee
    When User delete the updated employee:"<jsonpath>"
    Then User Validates the Response Code and body of the deleted employee
    Examples:
      | TestcaseId         | jsonpath |
      | TC_TP_API_S0001_01 | Api      |

  @put
  Scenario Outline:API_003 Validate employee creation put operation
    Given User creates reference with RestAPI BaseURI
    When User creates the employee:"<jsonpath>"
    When User search for all the created employee:"<jsonpath>"
    Then User Validates the Response Code and body of the searched list of employee
    When User updates the searched employee:"<jsonpath>"
    Then User Validates the Response Code and body of the updated employee
    When User delete the updated employee:"<jsonpath>"
    Then User Validates the Response Code and body of the deleted employee
    Examples:
      | TestcaseId         | jsonpath |
      | TC_TP_API_S0001_01 | Api      |

  @del
  Scenario Outline:API_004 Validate employee creation Del operation
    Given User creates reference with RestAPI BaseURI
    When User creates the employee:"<jsonpath>"
    When User search for all the created employee:"<jsonpath>"
    Then User Validates the Response Code and body of the searched list of employee
    When User delete the updated employee:"<jsonpath>"
    Then User Validates the Response Code and body of the deleted employee
    Examples:
      | TestcaseId         | jsonpath |
      | TC_TP_API_S0001_01 | Api      |







