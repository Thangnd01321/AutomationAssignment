@web @login
Feature: Login

  Background:
    Given User is on login page

  Scenario Outline: User cannot login with wrong username
    When User login using username "<username>" and password "<password>"
    Then User should get error message "Your username is invalid!"
    Examples:
      | username | password             |
      | Tomsmith | SuperSecretPassword! |
      | [blank]  | SuperSecretPassword! |
      | [blank]  | [blank]              |

  Scenario Outline: User cannot login with wrong password
    When User login using username "<username>" and password "<password>"
    Then User should get error message "Your password is invalid!"
    Examples:
      | username | password             |
      | tomsmith | [blank]              |
      | tomsmith | superSecretPassword! |

  Scenario: User can login successfully using valid credentials
    When User login using following credentials
      | username | password             |
      | tomsmith | SuperSecretPassword! |
    Then User is navigated to secure page
      | message                        |
      | You logged into a secure area! |

  