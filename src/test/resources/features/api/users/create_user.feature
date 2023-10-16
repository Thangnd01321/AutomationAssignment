@api @create_user
Feature: Complete flow

  Background:
    Given Following user does not exist
      | email             |
      | david@example.com |

  Scenario: A new creation user can do a complete flow
    When Send POST to create a new user
      | username      | email             | password     |
      | David Charles | david@example.com | userPass123@ |
    Then A new user is created
      | username      | email             | password     |
      | David Charles | david@example.com | userPass123@ |

