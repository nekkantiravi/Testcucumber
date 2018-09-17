############################################
# Author: Discovery Regression QE Team
# Date: 6th June 2017
# Date Modified: None
############################################

Feature: Products are crawled through different user agents on Search Page

  @domain_discovery @artifact_discovery_pages @use_domestic @feature_bot
  Scenario: SearchResultsPage - DOMESTIC - Verify product crawled on search page for pagination action with given user agent
    Given I visit the web site as a guest user in "domestic" mode
    When I search for "shirts"
    And I should be in Search Landing page
    Then I verify product crawl for pagination action

  @domain_discovery @artifact_discovery_pages @use_registry @feature_bot
  Scenario: SearchResultsPage - REGISTRY - Verify product crawled on search page for pagination action with given user agent
    Given I visit the web site as a guest user in "registry" mode
    When I search for "plates"
    And I should be in Search Landing page
    Then I verify product crawl for pagination action

  @domain_discovery @artifact_discovery_pages @use_iship @feature_bot
  Scenario: SearchResultsPage - ISHIP - Verify product crawled on search page for pagination action with given user agent
    Given I visit the web site as a guest user in "iship" mode
    When I search for "pants"
    And I should be in Search Landing page
    Then I verify product crawl for pagination action

  @domain_discovery @artifact_discovery_pages @use_domestic @feature_bot
  Scenario: SearchResultsPage - DOMESTIC - Verify product crawled on search page for sort by action with given user agent
    Given I visit the web site as a guest user in "domestic" mode
    When I search for "pants"
    And I should be in Search Landing page
    Then I verify product crawl for below sort options:
      | Featured Items       |
      | Price: Low to High   |
      | Price: High to Low   |
      | Customers' Top Rated |
      | Best Sellers         |
      | New Arrivals         |

  @domain_discovery @artifact_discovery_pages @use_registry @feature_bot
  Scenario: SearchResultsPage - REGISTRY - Verify product crawled on search page for sort by action with given user agent
    Given I visit the web site as a guest user in "registry" mode
    When I search for "bowls"
    And I should be in Search Landing page
    Then I verify product crawl for below sort options:
      | Featured Items       |
      | Price: Low to High   |
      | Price: High to Low   |
      | Customers' Top Rated |
      | Best Sellers         |
      | New Arrivals         |

  @domain_discovery @artifact_discovery_pages @use_iship @feature_bot
  Scenario: SearchResultsPage - ISHIP - Verify product crawled on search page for sort by action with given user agent
    Given I visit the web site as a guest user in "iship" mode
    When I search for "skirts"
    And I should be in Search Landing page
    Then I verify product crawl for below sort options:
      | Featured Items       |
      | Price: Low to High   |
      | Price: High to Low   |
      | Customers' Top Rated |
      | Best Sellers         |
      | New Arrivals         |

##############################################
#Facet Selection Scenarios
##############################################

  @domain_discovery @artifact_discovery_pages @use_domestic @feature_bot
  Scenario: SearchResultsPage - Verify product crawled on search page for color facet in DOMESTIC mode
    Given I visit the web site as a guest user in "domestic" mode
    When I search for "dresses"
    And I should be in Search Landing page
    Then I verify product crawl for "Black" item from "Color" facet on left nav

  @domain_discovery @artifact_discovery_pages @use_domestic @feature_bot
  Scenario: SearchResultsPage - Verify product crawled on search page for price facet in DOMESTIC mode
    Given I visit the web site as a guest user in "domestic" mode
    When I search for "skirts"
    And I should be in Search Landing page
    Then I verify product crawl for "Under $50" item from "Price" facet on left nav

  @domain_discovery @artifact_discovery_pages @use_domestic @feature_bot
  Scenario: SearchResultsPage - Verify product crawled on search page for brand facet in DOMESTIC mode
    Given I visit the web site as a guest user in "domestic" mode
    When I search for "lips makeup"
    And I should be in Search Landing page
    Then I verify product crawl for "Clinique" item from "Brand" facet on left nav

  @domain_discovery @artifact_discovery_pages @use_domestic @feature_bot
  Scenario: SearchResultsPage - Verify product crawled on search page for size facet in DOMESTIC mode
    Given I visit the web site as a guest user in "domestic" mode
    When I search for "women narrow flats"
    And I should be in Search Landing page
    Then I verify product crawl for "7" item from "Size" facet on left nav

  @domain_discovery @artifact_discovery_pages @use_domestic @feature_bot
  Scenario: SearchResultsPage - Verify product crawled on search page for special offers facet in DOMESTIC mode
    Given I visit the web site as a guest user in "domestic" mode
    When I search for "skirts"
    And I should be in Search Landing page
    Then I verify product crawl for "Sales & Offers" item from "Sales & Offers" facet on left nav

  @domain_discovery @artifact_discovery_pages @use_domestic @feature_bot
  Scenario: SearchResultsPage - Verify product crawled on search page for bops facet in DOMESTIC mode
    Given I visit the web site as a guest user in "domestic" mode
    When I search for "electrics"
    And I should be in Search Landing page
    Then I verify that "Pick Up In Store" facet is listed on left nav
    When I select zipcode link in pick-up in-store facet section
    Then I verify that bops overlay is displayed
    When I search for zipcode "94102" in bops change store dialog
    And I close the bops change store dialog
    Then I verify product crawl for "random" item from "BOPS" facet on left nav

  @domain_discovery @artifact_discovery_pages @use_registry @feature_bot
  Scenario: SearchResultsPage - Verify product crawled on search page for color facet in REGISTRY mode
    Given I visit the web site as a guest user in "registry" mode
    When I search for "luggage accessories"
    And I should be in Search Landing page
    Then I verify product crawl for "Black" item from "Color" facet on left nav

  @domain_discovery @artifact_discovery_pages @use_registry @feature_bot
  Scenario: SearchResultsPage - Verify product crawled on search page for price facet in REGISTRY mode
    Given I visit the web site as a guest user in "registry" mode
    When I search for "ancents"
    And I should be in Search Landing page
    Then I verify product crawl for "Under $50" item from "Price" facet on left nav

  @domain_discovery @artifact_discovery_pages @use_registry @feature_bot
  Scenario: SearchResultsPage - Verify product crawled on search page for brand facet in REGISTRY mode
    Given I visit the web site as a guest user in "registry" mode
    When I search for "cookware"
    And I should be in Search Landing page
    Then I verify product crawl for "Calphalon" item from "Brand" facet on left nav

  @domain_discovery @artifact_discovery_pages @use_registry @feature_bot
  Scenario: SearchResultsPage - Verify product crawled on search page for special offers facet in REGISTRY mode
    Given I visit the web site as a guest user in "registry" mode
    When I search for "sheets"
    And I should be in Search Landing page
    Then I verify product crawl for "Sales & Offers" item from "Sales & Offers" facet on left nav

  @domain_discovery @artifact_discovery_pages @use_reigstry @feature_bot
  Scenario: SearchResultsPage - Verify product crawled on search page for bops facet in REGISTRY mode
    Given I visit the web site as a guest user in "registry" mode
    When I search for "electrics"
    And I should be in Search Landing page
    Then I verify that "Pick Up In-Store" facet is listed on left nav
    When I select zipcode link in pick-up in-store facet section
    Then I verify that bops overlay is displayed
    When I search for zipcode "94102" in bops change store dialog
    And I close the bops change store dialog
    Then I verify product crawl for "random" item from "BOPS" facet on left nav

  @domain_discovery @artifact_discovery_pages @use_iship @feature_bot
  Scenario: SearchResultsPage - Verify product crawled on search page for color facet in ISHIP mode
    Given I visit the web site as a guest user in "iship" mode
    When I search for "shirts"
    And I should be in Search Landing page
    Then I verify product crawl for "Black" item from "Color" facet on left nav

  @domain_discovery @artifact_discovery_pages @use_iship @feature_bot
  Scenario: SearchResultsPage - Verify product crawled on search page for brand facet in ISHIP mode
    Given I visit the web site as a guest user in "iship" mode
    When I search for "handbags for women"
    And I should be in Search Landing page
    Then I verify product crawl for "Anne Klein" item from "Brand" facet on left nav

  @domain_discovery @artifact_discovery_pages @use_iship @feature_bot
  Scenario: SearchResultsPage - Verify product crawled on search page for size facet in ISHIP mode
    Given I visit the web site as a guest user in "iship" mode
    When I search for "women narrow flats"
    And I should be in Search Landing page
    Then I verify product crawl for "7" item from "Size" facet on left nav

  @domain_discovery @artifact_discovery_pages @use_iship @feature_bot
  Scenario: SearchResultsPage - Verify product crawled on search page for special offers facet in ISHIP mode
    Given I visit the web site as a guest user in "iship" mode
    When I search for "accessories for boys"
    And I should be in Search Landing page
    Then I verify product crawl for "Sales & Offers" item from "Sales & Offers" facet on left nav
