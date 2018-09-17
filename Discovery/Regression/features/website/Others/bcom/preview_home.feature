Feature: Verify category pages

  @preview_desktop
  Scenario: Verify Footer Section on all category page
    Given I visit the web site as a guest user
    When I navigate to all category pages and I verify footer section is displayed
      | WOMEN                 |
      | MEN                   |
      | HOME                  |
      | WHAT'S NEW            |
      | SHOES                 |
      | HANDBAGS              |
      | BEAUTY                |
      | KIDS                  |
      | JEWELRY & ACCESSORIES |


  @preview_desktop
  Scenario: HomePage: Verify FOBs and test Flyout Menu
    Given I visit the web site as a guest user
    When I navigate to all category pages and I verify left nav red link is clickable
      | WOMEN                 |
      | MEN                   |
      | HOME                  |
      | WHAT'S NEW            |
      | SHOES                 |
      | HANDBAGS              |
      | BEAUTY                |
      | KIDS                  |
      | JEWELRY & ACCESSORIES |

  @preview_desktop
  Scenario: Verify footer links are displayed on Homepage
    Given I visit the web site as a guest user in "domestic" mode
    Then I should see new header and footer section in "domestic" mode
    And I verify the footer is displayed
