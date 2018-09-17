Feature: Navigating on the Global Navigation Button

  @dsv_mew_sev1 @domain_discovery
  Scenario Outline: Access the top level shop categories
    Given I visit the mobile web site as a guest user
    When I open the global navigation
    When I navigate on menu item "<category>"
    Then I should see the "category_splash" Page
      Examples:
      |  category                |
      | Women                    |
      | Shoes                    |
      | Handbags                 |
      | Jewelry & Accessories    |
      | Beauty                   |
      | Men                      |
      | Kids                     |
      | Home                     |
      | Sale                     |
      | Editorial                |
      | Gifts                    |

  @dsv_mew_sev1 @domain_discovery
  Scenario: Access the top level shop category which doesn't have category splash page
    Given I visit the mobile web site as a guest user
    When I open the global navigation
    And I navigate on menu item "DESIGNERS"
    Then I should see this "all-designers" keyword in page url

