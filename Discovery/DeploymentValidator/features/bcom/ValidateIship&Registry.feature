############################################
# Author: Discovery Regression QE Team
# Date: 21st June 2017
# Date Modified: None
############################################

Feature: Verify ISHIP and Registry mode

  @domain_discovery @priority_high
  Scenario Outline: Verify FLYOUTS menu is being displayed for all FOBs in REGISTRY mode
    Given I am on CatSplash Page for "<fob>" on "registry" mode
    And I hover over on the below "<fob>" fob's
    Then I verify that flyout menu is displayed
    Examples:
      | fob                   |
      | DINING                |
     # | KITCHEN               |
     # | BED & BATH            |
     # | HOME DECOR            |
     # | LUGGAGE               |
     # | CLEANING & ORGANIZING |

  @domain_discovery @priority_high
  Scenario Outline: Verify FLYOUTS menu is being displayed for all FOBs in ISHIP mode
    Given I am on CatSplash Page for "<fob>" on "iship" mode
    And I hover over on the below "<fob>" fob's
    Then I verify that flyout menu is displayed
    Examples:
      | fob                     |
      | WOMEN                    |
      #| HOME                   |
     # | MEN                     |
    # | BED & BATH              |
    #  | JUNIORS                 |
     # | KIDS                    |
     # | SHOES                   |
     # | HANDBAGS & ACCESSORIES  |
     # | JEWELRY                 |
     # | WATCHES                 |


  @domain_discovery @priority_high @mcom @bcom
  Scenario Outline: Verify the auto suggestions in iship and registry
    Given I visit the web site as a "<user_type>" user in "<mode>" mode
    When I enter "<search>" keyword in global search field
    Then I "should see" autocomplete suggestions list
    Examples:
      | user_type  |  | mode     | search   |
      | guest      |  | iship    | coats    |
      | registered |  | site     | pants    |


  @domain_discovery @priority_high
  Scenario: Verify that the FLYOUTS is displayed in Search Results Page in ISHIP mode
    Given I visit the web site as a "guest" user in "iship" mode
    When I enter "Plates" keyword in global search field
    And I mouse over on the "random" fob
    Then I should see flyout menu for "random" fob


  @domain_discovery @priority_high
  Scenario: Verify that the FLYOUTS is displayed in Search Results Page in Registry mode
    Given I visit the web site as a "guest" user in "registry" mode
    When I enter "Plates" keyword in global search field
    And I mouse over on the "random" fob
    Then I should see flyout menu for "random" fob

  @domain_discovery @priority_high
  Scenario: Verify that DESIGNERS FLYOUT displays a grid layout in ISHIP mode
    Given I visit the web site as a guest user in "iship" mode
    When I mouse over on the "DESIGNERS" fob
    Then I should see grid layout in the flyout menu
    When I select any character from grid layout
    Then I should navigate to specific designer index page in "iship" mode

  @domain_discovery @priority_high
  Scenario: Verify that DESIGNERS FLYOUT displays a grid layout in REGISTRY mode
    Given I visit the web site as a "guest" user in "registry" mode
    And I mouse over on the "BRANDS" fob
    And I should see grid layout in the flyout menu
    When I select any character from grid layout
    Then I should navigate to specific designer index page in "registry" mode

  @domain_discovery @priority_high @mcom @bcom
  Scenario: Verify FLYOUTS for Browse Page in ISHIP mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    When I change country to "India"
    And I navigate to category browse page
    Then I verify dynamic top navigation in "iship" mode
    When I mouse hover on any category
    Then I should see flyout menu
    And I should see new header and footer elements section in "Iship"

  @domain_discovery @priority_high @mcom @bcom
  Scenario: Verify FLYOUTS for Browse Page in REGISTRY mode
    Given I visit the web site as a guest user
    When I navigate to registry home page
    And I navigate to category browse page
    Then I verify dynamic top navigation in "registry" mode
    When I mouse hover on any category
    Then I should see flyout menu
    And I should see new header and footer elements section in "Registry"

  @domain_discovery @priority_high @bcom @mcom
  Scenario Outline: Verify pagination in SearchResultsPage in ISHIP & Registry modes
    Given I am on SearchResultsPage for "plates" in <shopping_mode> mode
    Then I verify that pagination works
    Examples:
      | shopping_mode  |
      | Registry       |
      | Iship          |

  @domain_discovery @priority_high @mcom
  Scenario: Verify pagination, Items per page, pricing, ratings & reviews in ISHIP & Registry mode
    Given I am on SearchResultsPage for "skirts" in domestic mode
    Then I should be in Search Landing page
    And I verify that pagination is displayed
    And I verify that "60" item count option and respective number of products on page
    And Verify basic attributes of Search Results page

  @domain_discovery @priority_high @mcom
  Scenario: Verify pagination, Items per page, pricing, ratings & reviews in ISHIP & Registry mode
    Given I am on SearchResultsPage for "skirts" in domestic mode
    Then I should be in Search Landing page
    And I verify that pagination is displayed
    And I verify that "90" item count option and respective number of products on page
    And Verify basic attributes of Search Results page

  @domain_discovery @priority_high @mcom @bcom
  Scenario: Verify sorting and pagination in ISHIP & Registry mode
    Given I am on SearchResultsPage for "jeans" in Domestic mode
    Then I verify that facets are listed on left nav
    When I click 3 pagination number
    Then I verify that navigated to page 3 on search result page
    When I select "Best Sellers" in sort by drop down
    And I select 'single' facet value from 'any' facet section
    Then I verify that products are filtered as per selected facet value

  @domain_discovery @priority_high @mcom @bcom
  Scenario: Verify FLYOUTS in ISHIP mode
    Given I am on SearchResultsPage for "dining" in ISHIP mode
    When I click on next pagination button
    Then I verify dynamic top navigation in "iship" mode
    When I mouse hover on any category
    Then I should see flyout menu
    And I should see new header and footer elements section in "Iship"