############################################
# Author: Discovery Regression QE Team
# Date: 26th April 2017
# Date Modified: None
############################################

Feature: Products are crawled through different user agents on Browse Page

  @domain_discovery @artifact_discovery_pages @use_domestic @feature_bot
  Scenario: BrowsePage - DOMESTIC - Verify product crawled on browse page for pagination action with given user agent
    Given I visit the web site as a guest user
    And I navigate to the "Dress Shirts" browse page under "MEN"
    And I should be in category browse page
    Then I verify product crawl for pagination action
    When I navigate to the "Cookware" browse page under "HOME"
    And I should be in category browse page
    Then I verify product crawl for pagination action
    When I navigate to the "Clinique" browse page under "BEAUTY"
    And I should be in category browse page
    Then I verify product crawl for pagination action

  @domain_discovery @artifact_discovery_pages @use_registry @feature_bot
  Scenario: BrowsePage - REGISTRY - Verify product crawled on browse page for pagination action with given user agent
    Given I visit the web site as a guest user in "registry" mode
    And I navigate to the "Electrics" browse page under "KITCHEN"
    And I should be in category browse page
    Then I verify product crawl for pagination action
    When I navigate to the "Dinnerware" browse page under "DINING & ENTERTAINING"
    And I should be in category browse page
    Then I verify product crawl for pagination action
    When I navigate to the "Bedding" browse page under "BED & BATH"
    And I should be in category browse page
    Then I verify product crawl for pagination action

  @domain_discovery @artifact_discovery_pages @use_iship @feature_bot
  Scenario: BrowsePage - ISHIP - Verify product crawled on browse page for pagination action with given user agent
    Given I visit the web site as a guest user in "iship" mode
    And I navigate to the "Dress Shirts" browse page under "MEN"
    And I should be in category browse page
    Then I verify product crawl for pagination action
    When I navigate to the "Cookware" browse page under "HOME"
    And I should be in category browse page
    Then I verify product crawl for pagination action
    When I navigate to the "Longchamp" browse page under "HANDBAGS"
    And I should be in category browse page
    Then I verify product crawl for pagination action

  @domain_discovery @artifact_discovery_pages @use_domestic @feature_bot
  Scenario: BrowsePage - DOMESTIC - Verify product crawled on browse page for sort by action with given user agent
    Given I visit the web site as a guest user
    And I navigate to the "Dress Shirts" browse page under "MEN"
    And I should be in category browse page
    Then I verify product crawl for below sort options:
      | Our Top Picks      |
      | New Arrivals       |
      | Best Sellers       |
      | Customer Top Rated |
      | Price (high-low)   |
      | Price (low-high)   |
    When I navigate to the "Towels" browse page under "HOME"
    And I should be in category browse page
    Then I verify product crawl for below sort options:
      | Our Top Picks      |
      | New Arrivals       |
      | Best Sellers       |
      | Customer Top Rated |
      | Price (high-low)   |
      | Price (low-high)   |
    When I navigate to the "Clinique" browse page under "BEAUTY"
    And I should be in category browse page
    Then I verify product crawl for below sort options:
      | Our Top Picks      |
      | New Arrivals       |
      | Best Sellers       |
      | Customer Top Rated |
      | Price (high-low)   |
      | Price (low-high)   |

  @domain_discovery @artifact_discovery_pages @use_registry @feature_bot
  Scenario: BrowsePage - REGISTRY - Verify product crawled on browse page for sort by action with given user agent
    Given I visit the web site as a guest user in "registry" mode
    And I navigate to the "Electrics" browse page under "KITCHEN"
    And I should be in category browse page
    Then I verify product crawl for below sort options:
      | Our Top Picks      |
      | New Arrivals       |
      | Best Sellers       |
      | Customer Top Rated |
      | Price (high-low)   |
      | Price (low-high)   |
    When I navigate to the "Serveware" browse page under "DINING & ENTERTAINING"
    And I should be in category browse page
    Then I verify product crawl for below sort options:
      | Our Top Picks      |
      | New Arrivals       |
      | Best Sellers       |
      | Customer Top Rated |
      | Price (high-low)   |
      | Price (low-high)   |
    When I navigate to the "Bath" browse page under "BED & BATH"
    And I should be in category browse page
    Then I verify product crawl for below sort options:
      | Our Top Picks      |
      | New Arrivals       |
      | Best Sellers       |
      | Customer Top Rated |
      | Price (high-low)   |
      | Price (low-high)   |

  @domain_discovery @artifact_discovery_pages @use_iship @feature_bot
  Scenario: BrowsePage - ISHIP - Verify product crawled on browse page for sort by action with given user agent
    Given I visit the web site as a guest user in "iship" mode
    And I navigate to the "Dress Shirts" browse page under "MEN"
    And I should be in category browse page
    Then I verify product crawl for below sort options:
      | Our Top Picks      |
      | New Arrivals       |
      | Best Sellers       |
      | Customer Top Rated |
      | Price (high-low)   |
      | Price (low-high)   |
    When I navigate to the "Table Linens" browse page under "HOME"
    And I should be in category browse page
    Then I verify product crawl for below sort options:
      | Our Top Picks      |
      | New Arrivals       |
      | Best Sellers       |
      | Customer Top Rated |
      | Price (high-low)   |
      | Price (low-high)   |
    When I navigate to the "Sneakers" browse page under "SHOES"
    And I should be in category browse page
    Then I verify product crawl for below sort options:
      | Our Top Picks      |
      | New Arrivals       |
      | Best Sellers       |
      | Customer Top Rated |
      | Price (high-low)   |
      | Price (low-high)   |

##############################################
#Facet Selection Scenarios
##############################################

  @domain_discovery @artifact_discovery_pages @use_domestic @feature_bot
  Scenario: BrowsePage - Verify product crawled on browse page for color facet in DOMESTIC mode
    Given I visit the web site as a guest user in "domestic" mode
    And I navigate to the "Dress Shirts" browse page under "MEN"
    And I should be in category browse page
    And I verify product crawl for "Black" item from "Color" facet on left nav

  @domain_discovery @artifact_discovery_pages @use_domestic @feature_bot
  Scenario: BrowsePage - Verify product crawled on browse page for price facet in DOMESTIC mode
    Given I visit the web site as a guest user in "domestic" mode
    And I navigate to the "Sneakers" browse page under "SHOES"
    Then I should be in category browse page
    And I verify product crawl for "Under $50" item from "Price" facet on left nav

  @domain_discovery @artifact_discovery_pages @use_domestic @feature_bot
  Scenario: BrowsePage - Verify product crawled on browse page for brand facet in DOMESTIC mode
    Given I visit the web site as a guest user in "domestic" mode
    And I navigate to the "Lips" browse page under "BEAUTY"
    Then I should be in category browse page
    And I verify product crawl for "Clinique" item from "Brand" facet on left nav

  @domain_discovery @artifact_discovery_pages @use_domestic @feature_bot
  Scenario: BrowsePage - Verify product crawled on browse page for size facet in DOMESTIC mode
    Given I visit the web site as a guest user in "domestic" mode
    And I navigate to the "Sneakers" browse page under "SHOES"
    Then I should be in category browse page
    And I verify product crawl for "5" item from "Size" facet on left nav

  @domain_discovery @artifact_discovery_pages @use_domestic @feature_bot
  Scenario: BrowsePage - Verify product crawled on browse page for special offers facet in DOMESTIC mode
    Given I visit the web site as a guest user in "domestic" mode
    And I navigate to the "Pants" browse page under "MEN"
    Then I should be in category browse page
    And I verify product crawl for "Sales & Offers" item from "Sales & Offers" facet on left nav

  @domain_discovery @artifact_discovery_pages @use_domestic @feature_bot
  Scenario: BrowsePage - Verify product crawled on browse page for bops facet in DOMESTIC mode
    Given I visit the web site as a guest user in "domestic" mode
    When I navigate to the "Dress Shirts" browse page under "MEN"
    And I should be in category browse page
    Then I verify that "Pick Up In Store" facet is listed on left nav
    When I select zipcode link in pick-up in-store facet section
    Then I verify that bops overlay is displayed
    When I search for zipcode "94102" in bops change store dialog
    And I close the bops change store dialog
    And I verify product crawl for "random" item from "BOPS" facet on left nav

  @domain_discovery @artifact_discovery_pages @use_registry @feature_bot
  Scenario: BrowsePage - Verify product crawled on browse page for color facet in REGISTRY mode
    Given I visit the web site as a guest user in "registry" mode
    And I navigate to the "Luggage Accessories" browse page under "LUGGAGE"
    Then I should be in category browse page
    And I verify product crawl for "Black" item from "Color" facet on left nav

  @domain_discovery @artifact_discovery_pages @use_registry @feature_bot
  Scenario: BrowsePage - Verify product crawled on browse page for price facet in REGISTRY mode
    Given I visit the web site as a guest user in "registry" mode
    And I navigate to the "Home Accents" browse page under "HOME DECOR"
    Then I should be in category browse page
    And I verify product crawl for "Under $50" item from "Price" facet on left nav

  @domain_discovery @artifact_discovery_pages @use_registry @feature_bot
  Scenario: BrowsePage - Verify product crawled on browse page for brand facet in REGISTRY mode
    Given I visit the web site as a guest user in "registry" mode
    And I navigate to the "Cookware" browse page under "KITCHEN"
    Then I should be in category browse page
    And I verify product crawl for "Calphalon" item from "Brand" facet on left nav

  @domain_discovery @artifact_discovery_pages @use_registry @feature_bot
  Scenario: BrowsePage - Verify product crawled on browse page for special offers facet in REGISTRY mode
    Given I visit the web site as a guest user in "registry" mode
    And I navigate to the "Bedding" browse page under "BED & BATH"
    Then I should be in category browse page
    And I verify product crawl for "Sales & Offers" item from "Sales & Offers" facet on left nav

  @domain_discovery @artifact_discovery_pages @use_reigstry @feature_bot
  Scenario: BrowsePage - Verify product crawled on browse page for bops facet in REGISTRY mode
    Given I visit the web site as a guest user in "registry" mode
    When I navigate to the "Electrics" browse page under "KITCHEN"
    And I should be in category browse page
    Then I verify that "Pick Up In-Store" facet is listed on left nav
    When I select zipcode link in pick-up in-store facet section
    Then I verify that bops overlay is displayed
    When I search for zipcode "94102" in bops change store dialog
    And I close the bops change store dialog
    And I verify product crawl for "random" item from "BOPS" facet on left nav

  @domain_discovery @artifact_discovery_pages @use_iship @feature_bot
  Scenario: BrowsePage - Verify product crawled on browse page for color facet in ISHIP mode
    Given I visit the web site as a guest user in "iship" mode
    And I navigate to the "Dress Shirts" browse page under "MEN"
    Then I should be in category browse page
    And I verify product crawl for "Black" item from "Color" facet on left nav

  @domain_discovery @artifact_discovery_pages @use_iship @feature_bot
  Scenario: BrowsePage - Verify product crawled on browse page for brand facet in ISHIP mode
    Given I visit the web site as a guest user in "iship" mode
    And I navigate to the "All Handbags" browse page under "HANDBAGS"
    Then I should be in category browse page
    And I verify product crawl for "ALLSAINTS" item from "Designer" facet on left nav

  @domain_discovery @artifact_discovery_pages @use_iship @feature_bot
  Scenario: BrowsePage - Verify product crawled on browse page for size facet in ISHIP mode
    Given I visit the web site as a guest user in "iship" mode
    And I navigate to the "Flats" browse page under "SHOES"
    Then I should be in category browse page
    And I verify product crawl for "5" item from "Size" facet on left nav

  @domain_discovery @artifact_discovery_pages @use_iship @feature_bot
  Scenario: BrowsePage - Verify product crawled on browse page for special offers facet in ISHIP mode
    Given I visit the web site as a guest user in "iship" mode
    And I navigate to the "Boys' Accessories" browse page under "KIDS"
    Then I should be in category browse page
    And I verify product crawl for "Sales & Offers" item from "Sales & Offers" facet on left nav
