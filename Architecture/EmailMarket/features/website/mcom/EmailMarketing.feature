Feature: Email marketing

  Scenario: Email marketing
    Given I visit the web site as a guest user
    Given I navigate to <category>
    Then I view the banner
    And  I close the banner
    Examples

    | category |
    | 118      |