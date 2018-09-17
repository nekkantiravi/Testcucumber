Feature: Search functionality

  Scenario Outline: To verify Search
    Given I visit the web site as a <user_type> user
    When I enter "Dress" in search text box
    Then I verify that "Dress" should appears on browse page
    Examples:
      | user_type  |
      | guest      |
      | registered |

  Scenario Outline: To Search – junk word
    Given I visit the web site as a <user_type> user
    When I enter "xyzjunk" in search text box
    Then I verify that 0 result for "xyzjunk" should display
    Examples:
      | user_type  |
      | guest      |
      | registered |




