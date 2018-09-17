#Author: Discovery QE
#Date Created: 07/27/2017

Feature: Verify header functionality on all the pages

  @domain_mew_discovery @use_mew_regression @project_HNFComp @feature_mew_hnf_comp @priority_high
  Scenario: Verify header appear on the member PDP page
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | Shop       |
      | Men        |
      | Activewear |
    And I select a random member product using mobile website
    Then I should be redirected to PDP page using mobile website
    And I verify New Header Redesign implementation in current page

  @domain_mew_discovery @use_mew_regression @project_HNFComp @feature_mew_hnf_comp @priority_high
  Scenario: Verify header appear on the master PDP page
    Given I visit the mobile web site as a guest user in domestic mode
    When I navigate the global navigation menu as follows:
      | Shop              |
      | For The Home      |
      | More for the Home |
      | Home Decor        |
    And I select a random master product using mobile website
    Then I should be redirected to PDP page using mobile website
    And I verify New Header Redesign implementation in current page

  @domain_mew_discovery @use_mew_regression @project_HNFComp @feature_mew_hnf_comp @priority_high
  Scenario: Verify header appear on shopping bag page
    Given I visit the mobile web site as a guest user
    Then I type "jeans" keyword in mew search box
    When I select a random member product using mobile website
    Then I should be redirected to PDP page using mobile website
    Then I add product to my bag from standard PDP Page using mobile site
    Then I should be redirected to ATB page using mobile website
    When I navigate to shopping bag page from add to bag page using mobile website
    Then I verify New Header Redesign implementation in current page

  @domain_mew_discovery @use_mew_regression @project_HNFComp @feature_mew_hnf_comp @priority_high
  Scenario: Verify header appear on registry welcome page
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | Registry or Wedding Registry |
    And I select "create your registry" from mobile registry home page
    And I start to create a new registry from mobile registry capture email page
    And I create a new registry using mobile website
    Then I should be navigated to the mobile registry welcome page
    And I verify New Header Redesign implementation in current page

  @domain_mew_discovery @use_mew_regression @project_HNFComp @feature_mew_hnf_comp @priority_high
  Scenario: Verify new header implementation on view registry page
    Given I visit the mobile web site as a registry user
    When I navigate the global navigation menu as follows:
      | Wedding Registry |
      | Registry Tools   |
      | View Registry    |
    Then I verify New Header Redesign implementation in current page

  @domain_mew_discovery @use_mew_regression @project_HNFComp @feature_mew_hnf_comp @priority_high
  Scenario: Verify new header implementation on Manage registry page
    Given I visit the mobile web site as a registry user
    When I navigate the global navigation menu as follows:
      | Wedding Registry |
      | Registry Tools   |
      | Manage Registry    |
    Then I verify New Header Redesign implementation in current page

  @domain_mew_discovery @use_mew_regression @project_HNFComp @feature_mew_hnf_comp @priority_high
  Scenario: Verify new header implementation on registry Thank you manager page
    Given I visit the mobile web site as a registry user
    When I navigate the global navigation menu as follows:
      | Wedding Registry  |
      | Registry Tools    |
      | Thank You Manager |
    Then I verify New Header Redesign implementation in current page

  @domain_mew_discovery @use_mew_regression @project_HNFComp @feature_mew_hnf_comp @priority_high
  Scenario: Verify new header implementation on find registry page
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | Wedding Registry |
      | Find A Registry  |
    Then I verify New Header Redesign implementation in current page
    And I verify GN is expanded till "Find A Registry"

  @domain_mew_discovery @use_mew_regression @project_HNFComp @feature_mew_hnf_comp @priority_high
  Scenario: Verify new header implementation on category splash page
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | Shop  |
      | Shoes |
    And I close the global navigation menu
    And I navigate to "women's Heel" category browse page using mobile website
    And I navigate back to "category splash" page using mobile website
    Then I verify New Header Redesign implementation in current page

  @domain_mew_discovery @use_mew_regression @project_HNFComp @feature_mew_hnf_comp @priority_high
  Scenario: Verify new header implementation on my account page
    Given I visit the mobile web site as a registered user
    And I open the global navigation
    When I navigate on menu item "My Account"
    Then I should be navigated to the mobile my account page
    And I verify New Header Redesign implementation in current page

  @domain_mew_discovery @use_mew_regression @project_HNFComp @feature_mew_hnf_comp @priority_high
  Scenario: Verify new header implementation on deals page
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | Deals  |
    Then I verify New Header Redesign implementation in current page

  @domain_mew_discovery @use_mew_regression @project_HNFComp @feature_mew_hnf_comp @priority_high
  Scenario: Verify new header implementation on sales page
    Given I visit the mobile web site as a guest user
    When I click on sales media banner in home page
    Then I verify New Header Redesign implementation in current page

  @domain_mew_discovery @use_mew_regression @project_HNFComp @feature_mew_hnf_comp @priority_high
  Scenario: Verify new header implementation on CE page
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | More                      |
      | Apply: Macy's Credit Card |
    And I verify New Header Redesign implementation in current page

  @domain_mew_discovery @use_mew_regression @project_HNFComp @feature_mew_hnf_comp @priority_high
  Scenario: Verify new header implementation on star rewards page
    Given I visit the mobile web site as a registered user
    And I open the global navigation
    When I navigate on menu item "My Account"
    And I should be navigated to the mobile my account page
    And I click on star rewards link on my account page for mobile
    Then I verify New Header Redesign implementation in current page

  @domain_mew_discovery @use_mew_regression @project_HNFComp @feature_mew_hnf_comp @priority_high
  Scenario: Verify new header implementation on my preferences page
    Given I visit the mobile web site as a registered user
    And I open the global navigation
    When I navigate on menu item "My Account"
    And I should be navigated to the mobile my account page
    When I scroll the page down
    And I click on my preferences link on my account page for mobile
    Then I verify New Header Redesign implementation in current page

  @domain_mew_discovery @use_mew_regression @project_HNFComp @feature_mew_hnf_comp @priority_high
  Scenario: Verify new header implementation and GN expansion on Furniture & Mattresses browse page
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | Shop                   |
      | Furniture & Mattresses |
      | Bedroom                |
      | Mattresses             |
    Then I verify New Header Redesign implementation in current page
    And I verify GN is expanded till "Mattresses"

  @domain_mew_discovery @use_mew_regression @project_HNFComp @feature_mew_hnf_comp @priority_high
  Scenario: Verify new header implementation on secure-m wallet page
    Given I visit the mobile web site as a registered user without add CC
    When I navigate back to "my account" page using mobile website
    And I navigate to "oc my wallet" page from my account page using mobile website
    Then I verify New Header Redesign implementation in current page

  @domain_mew_discovery @use_mew_regression @project_HNFComp @feature_mew_hnf_comp @priority_high
  Scenario: Verify new header implementation on secure-m profile page
    Given I visit the mobile web site as a registered user without add CC
    When I navigate back to "my account" page using mobile website
    And I navigate to "my profile" page from my account page using mobile website
    Then I verify New Header Redesign implementation in current page

  @domain_mew_discovery @use_mew_regression @project_HNFComp @feature_mew_hnf_comp @priority_high
  Scenario: Verify new header implementation on secure-m my address book page
    Given I visit the mobile web site as a registered user without add CC
    When I navigate back to "my account" page using mobile website
    And I navigate to "my address book" page from my account page using mobile website
    Then I verify New Header Redesign implementation in current page

  @domain_mew_discovery @use_mew_regression @project_HNFComp @feature_mew_hnf_comp @priority_high
  Scenario: Verify new header implementation on secure-m order history page
    Given I visit the mobile web site as a registered user without add CC
    When I navigate back to "my account" page using mobile website
    And I navigate to "order status" page from my account page using mobile website
    Then I verify New Header Redesign implementation in current page

     # Scenario is implemented from D-61194
  @domain_mew_discovery @use_mew_regression @project_HNFComp @feature_mew_hnf_comp @priority_high
  Scenario: Verify page is not navigated to 404 error page from CE page
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | More                      |
      | Apply: Macy's Credit Card |
    And I navigate the global navigation menu as follows:
      | Shop |
    Then I should see page is navigated to home page

  @domain_mew_discovery @use_mew_regression @project_HNFComp @feature_mew_hnf_comp @priority_high
  Scenario: Verify GN is expanded correctly for Sale clearance category
    Given I visit the mobile web site as a guest user
    When I click on sale clearance category from body of the home page
    Then I verify GN is expanded till "Clearance & Closeout"

  @domain_mew_discovery @use_mew_regression @project_HNFComp @feature_mew_hnf_comp @priority_high
  Scenario: Verify GN is expanded correctly for Sale last act category
    Given I visit the mobile web site as a guest user
    When I click on sale last act category from body of the home page
    Then I verify GN is expanded till "Last Act"

  @domain_mew_discovery @use_mew_regression @project_HNFComp @feature_mew_hnf_comp @priority_high
  Scenario: Verify GN is expanded correctly for Gift Guide and Gift Cards page
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | Shop                     |
      | Gift Guides & Gift Cards |
    Then I verify GN is expanded till "Gift Guides & Gift Cards"

  @domain_mew_discovery @use_mew_regression @project_HNFComp @feature_mew_hnf_comp @priority_high
  Scenario: Verify GN is expanded correctly when user navigate to Wish List page from My Account page
    Given I visit the mobile web site as a registered user without add CC
    When I navigate back to "my account" page using mobile website
    And I navigate to "wish list" page from my account page using mobile website
    Then I verify GN is expanded till "Lists"

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: Verify GN is expanded correctly for available categories registry home page
    Given I visit the mobile web site as a guest user in registry mode
    When I click on "<category>" category in registry home page
    Then I should be on category splash page
    And I should see global navigation panel is expanded
    And I verify GN is expanded till "<category>"
    Examples:
      | category                |
      | Dining & Entertaining   |
      | Kitchen                 |
      | Bed & Bath              |
      | Luggage                 |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: Verify GN is expanded correctly for available categories iship home page
    Given I visit the mobile web site as a guest user in iship mode
    When I click on "<category>" category in registry home page
    Then I should be on category splash page
    And I should see global navigation panel is expanded
    And I verify GN is expanded till "<category>"
    Examples:
      | category               |
      | Women                  |
      | Men                    |
      | Jewelry & Watches      |
      | Handbags & Accessories |
      | Kids                   |
      | Shoes                  |
      | Plus Sizes & Petites   |
      | Home                   |
      | Juniors                |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: Verify GN is expanded correctly for available categories domestic home page
    Given I visit the mobile web site as a guest user in domestic mode
    When I click on "<category>" category in registry home page
    Then I should be on category splash page
    And I should see global navigation panel is expanded
    And I verify GN is expanded till "<category>"
    Examples:
      | category               |
      | Women                  |
      | Men                    |
      | Jewelry & Watches      |
      | Handbags & Accessories |
      | Kids                   |
      | Gift Cards             |
      | Shoes                  |
      | Plus Sizes & Petites   |
      | Home                   |
      | Juniors                |