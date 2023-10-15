@web @dropdown
Feature: Dropdown

  Background:
    Given User is on dropdown page

  Scenario Outline: User can select option from dropdown list
    When User select option "<option>"
    Then "<option>" is selected
    Examples:
      | option   |
      | Option 1 |
      | Option 2 |

