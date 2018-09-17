Feature: Navigating on the Global Navigation Button

  @dsv_mew_sev1 @domain_discovery @domain_mew_discovery @use_mew_regression
  Scenario: Navigating on the Global Navigation Button on Home Page
    Given I visit the mobile web site as a guest user in domestic mode
    When I open the global navigation
    Then I should see global navigation panel

  @dsv_mew_sev1 @domain_discovery @domain_mew_discovery @use_mew_regression
  Scenario Outline: Access the top level shop categories
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
  Scenario: Navigating twice on the Global Navigation Button should collapse the Global Navigation Panel
    Given  I visit the mobile web site as a guest user in domestic mode
    When I open the global navigation
    Then I should see global navigation panel
    When I again tab on the global navigation
    Then I should not see global navigation panel

  @dsv_mew_sev1 @domain_discovery @domain_mew_discovery @use_mew_regression
  Scenario Outline: Access the top level shop categories which do not have category splash page
    Given I visit the mobile web site as a guest user
    When I open the global navigation
    When I navigate on menu item "Shop"
    When I navigate on menu item "<category>"
    Then I should see the following "<keyword>" in page url
    Examples:
      |  category                 |   keyword             |
      | Active & Wellness         | health-and-wellness   |
      | Gift Guides & Gift cards  | gift-cards            |
      | Wedding Registry          | registryhome          |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: Verify all categories available in home page GN are navigating to respective page
    Given I visit the mobile web site as a registered user without add CC
    And I open the global navigation
    When I navigate on menu item "<category>"
    Then I verify all discovery elements on "<category>" page
    Examples:
      | category           |
      | Deals              |
      | My Account         |
      | Macy's Credit Card |
      | Wallet             |
      | Lists              |
      | Stores             |
      | Customer Service   |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: Verify all categories available in home page GN are navigating to respective page in iship mode
    Given I visit the mobile web site as a guest user in iship mode
    And I open the global navigation
    When I navigate on menu item "<category>"
    Then I verify all discovery elements on "<category>" page
    Examples:
      | category         |
      | ORDER TRACKING   |
      | STORES           |
      | REGISTRY         |
      | LEGAL POLICIES   |