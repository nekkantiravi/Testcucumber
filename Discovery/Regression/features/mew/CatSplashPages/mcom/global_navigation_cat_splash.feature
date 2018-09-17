Feature: Global Container - shop - Category Splash

  @dsv_mew_sev2 @domain_discovery @domain_mew_discovery @use_mew_regression
  Scenario Outline: Verify user is able to navigate to cat splash page using Global nav container
    Given I visit the mobile web site as a guest user
    When I open the global navigation
    When I navigate on menu item "Shop"
    When I navigate on menu item "<category>"
    Then I should see the "category_splash" Page
    Examples:
      |  category                |
      | Women                    |
      | Men                      |
      | Kids & Baby              |
      | Shoes                    |
      | For The Home             |
      | Beauty                   |
      | Handbags & Sunglasses    |
      | Jewelry & Watches        |
      | Lingerie & Shapewear     |
      | Plus & Petite            |
      | Juniors & Guys           |
      | Furniture & Mattresses   |

  @dsv_mew_sev2 @domain_discovery @domain_mew_discovery @use_mew_regression
  Scenario: Verify splash page when navigated directly
    Given I visit the mobile web site as a guest user
    When I navigate to splash page with the url
    Then I should see the "category_splash" Page
    And I should see global navigation panel
