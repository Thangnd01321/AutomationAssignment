@web @checkbox
Feature: Checkboxes

  Background:
    Given User is on checkboxes page

  # Checkbox 1 should be unchecked as default, so clicking into it once will change it to checked
  Scenario: User can check the checkboxes
    When User checks the "checkbox 1"
    Then "checkbox 1" is checked

  # Checkbox 2 should be checked as default, so clicking into it once will change it to unchecked
  Scenario: User can uncheck the checkboxes
    When User unchecks the "checkbox 2"
    Then "checkbox 2" is unchecked
