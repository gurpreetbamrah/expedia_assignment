@db_smoke @expedia_assignment
Feature: Verify records are successfully captured into database

  @db
  Scenario Outline: Verify records into database

    Given user connects to DB
    Then user execute the following query <query>
    And I verify the result count as <count>

    Examples:

      | query        | count |
      | employee.sql | 2     |