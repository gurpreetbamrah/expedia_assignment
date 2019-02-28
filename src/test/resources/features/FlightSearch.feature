@ui_smoke @expedia_assignment
Feature: As an end user of Expedia website i want to search the cheapest flight from my source to destination
  @ui
  Scenario Outline: Search cheapest flight from source to destination
    Given the user is on the expedia website home page
    When user clicks on flight icon
    And user select flight type as one way
    And user enter the source <source>
    And user enter the destination <destination>
    And user selects the date of journey
    Then user clicks on search button
    Then user should see list of flights with the cheapest on the top
    Then I select the cheapest flight
    And I verify the price under trip summary

    Examples:
      | source | destination |
      | BOM    | DEL         |
