@Adactin
Feature: Adactin


  @Smoke123
  Scenario Outline:Validate hotel booking in adactin application
    Given User launches the adactin application
    When User logs into the application with "<username>" and "<password>"
    Then User searches for the hotels by entering all the details
    Then User selects the hotel from the list of hotels
    Then User Books a hotel and validates hotel is booked successfully
    Examples:
      |username |password|
      |gouthamRaj|22G4O8|


  @Smoke
  Scenario Outline: Validate booked Itinerary page in adactin application
    Given User launches the adactin application
    When User logs into the application with "<username>" and "<password>"
    Then User clicks on booked itinerary
    And user validates booked itinerary page

    Examples:
      |username |password|
      |gouthamRaj|22G4O8|

  @Smoke
  Scenario Outline: Validate search hotel page in adactin application
    Given User launches the adactin application
    When User logs into the application with "<username>" and "<password>"
    And user validates search hotel page

    Examples:
      |username |password|
      |gouthamRaj|22G4O8|


