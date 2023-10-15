@web @upload
Feature: File upload

  Background:
    Given User is on upload page

  Scenario: User can check the checkboxes
    When User uploads file "sample.json"
    Then File "sample.json" is uploaded
