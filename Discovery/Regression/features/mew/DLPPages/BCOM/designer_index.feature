Feature: Verify designer index page on mobile website

  #B-90208
  @use_regression @domain_mew_discovery @use_mew_regression
  Scenario Outline: Clicking on a letter on the MEW designer index page should scroll the page to the chosen letter
    Given I visit the mobile web site as a guest user
    And I navigate the global navigation menu as follows:
      | Designers |
    When I click "<letter>" on the alphabet picker
    Then I should see the page scrolled to the selected "<letter>"
    Examples:
      | letter |
      | D |
      | Y |

  #B-90208
  @use_regression @domain_mew_discovery @use_mew_regression
  Scenario: Clicking on a letter on alphabet picker after scrolling on the page will again scroll to the chosen letter
    Given I visit the mobile web site as a guest user
    And I navigate the global navigation menu as follows:
      | Designers |
    Then I verify the functionality after clicking "B" on the alphabet picker
    And I click on scroll to the top button
    Then I verify the functionality after clicking "W" on the alphabet picker
