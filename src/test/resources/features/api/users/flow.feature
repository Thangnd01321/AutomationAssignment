@api @flow
Feature: User can write review

  Background:
    Given Following user does not exist
      | email             |
      | david@example.com |

  Scenario: User can write review for a completed order
    When Send POST to create a new user
      | username      | email             | password     |
      | David Charles | david@example.com | userPass123@ |
    And Send GET to get list of categories
    And Send GET to get list of products
    And Send POST to place an order with a product
      | category    | quantity |
      | Electronics | 2        |
    And Send PUT to update order status: "shipped"
    And Send POST to write a review
      | rating | comment           |
      | 5      | Very good product |
    Then A new review is recorded
      | rating | comment           |
      | 5      | Very good product |

