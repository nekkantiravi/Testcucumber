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
    Then I verify product crawl for pagination action
    When I navigate to the "Slipcovers" browse page under "HOME"
    Then I verify product crawl for pagination action
    When I navigate to the "Clinique" browse page under "BEAUTY"
    Then I verify product crawl for pagination action

  @domain_discovery @artifact_discovery_pages @use_registry @feature_bot
  Scenario: BrowsePage - REGISTRY - Verify product crawled on browse page for pagination action with given user agent
    Given I visit the web site as a guest user in "registry" mode
    And I navigate to the "Electrics" browse page under "KITCHEN"
    Then I verify product crawl for pagination action
    When I navigate to the "Dinnerware" browse page under "DINING"
    Then I verify product crawl for pagination action
    When I navigate to the "Comforters" browse page under "BED & BATH"
    Then I verify product crawl for pagination action

  @domain_discovery @artifact_discovery_pages @use_iship @feature_bot
  Scenario: BrowsePage - ISHIP - Verify product crawled on browse page for pagination action with given user agent
    Given I visit the web site as a guest user in "iship" mode
    And I navigate to the "Dress Shirts" browse page under "MEN"
    Then I verify product crawl for pagination action
    When I navigate to the "Window Treatments" browse page under "HOME"
    Then I verify product crawl for pagination action
    When I navigate to the "Charter Club" browse page under "JEWELRY"
    Then I verify product crawl for pagination action

  @domain_discovery @artifact_discovery_pages @use_domestic @feature_bot
  Scenario: BrowsePage - DOMESTIC - Verify product crawled on browse page for sort by action with given user agent
    Given I visit the web site as a guest user
    And I navigate to the "Dress Shirts" browse page under "MEN"
    Then I verify product crawl for below sort options:
      | Featured Items       |
      | Price: Low to High   |
      | Price: High to Low   |
      | Customers' Top Rated |
      | Best Sellers         |
      | New Arrivals         |
    When I navigate to the "Window Treatments" browse page under "HOME"
    Then I verify product crawl for below sort options:
      | Featured Items       |
      | Price: Low to High   |
      | Price: High to Low   |
      | Customers' Top Rated |
      | Best Sellers         |
      | New Arrivals         |
    When I navigate to the "Clinique" browse page under "BEAUTY"
    Then I verify product crawl for below sort options:
      | Featured Items       |
      | Price: Low to High   |
      | Price: High to Low   |
      | Customers' Top Rated |
      | Best Sellers         |
      | New Arrivals         |

  @domain_discovery @artifact_discovery_pages @use_registry @feature_bot
  Scenario: BrowsePage - REGISTRY - Verify product crawled on browse page for sort by action with given user agent
    Given I visit the web site as a guest user in "registry" mode
    And I navigate to the "Electrics" browse page under "KITCHEN"
    Then I verify product crawl for below sort options:
      | Featured Items       |
      | Price: Low to High   |
      | Price: High to Low   |
      | Customers' Top Rated |
      | Best Sellers         |
      | New Arrivals         |
    When I navigate to the "Dinnerware" browse page under "DINING"
    Then I verify product crawl for below sort options:
      | Featured Items       |
      | Price: Low to High   |
      | Price: High to Low   |
      | Customers' Top Rated |
      | Best Sellers         |
      | New Arrivals         |
    When I navigate to the "Comforters" browse page under "BED & BATH"
    Then I verify product crawl for below sort options:
      | Featured Items       |
      | Price: Low to High   |
      | Price: High to Low   |
      | Customers' Top Rated |
      | Best Sellers         |
      | New Arrivals         |

  @domain_discovery @artifact_discovery_pages @use_iship @feature_bot
  Scenario: BrowsePage - ISHIP - Verify product crawled on browse page for sort by action with given user agent
    Given I visit the web site as a guest user in "iship" mode
    And I navigate to the "Dress Shirts" browse page under "MEN"
    Then I verify product crawl for below sort options:
      | Featured Items       |
      | Price: Low to High   |
      | Price: High to Low   |
      | Customers' Top Rated |
      | Best Sellers         |
      | New Arrivals         |
    When I navigate to the "Home Decor" browse page under "HOME"
    Then I verify product crawl for below sort options:
      | Featured Items       |
      | Price: Low to High   |
      | Price: High to Low   |
      | Customers' Top Rated |
      | Best Sellers         |
      | New Arrivals         |
    When I navigate to the "Charter Club" browse page under "JEWELRY"
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
  Scenario: BrowsePage - Verify product crawled on browse page for color facet in DOMESTIC mode
    Given I visit the web site as a guest user in "domestic" mode
    And I navigate to the "Dress Shirts" browse page under "MEN"
    Then I should be in category browse page
    And I verify product crawl for "Black" item from "Color" facet on left nav

  @domain_discovery @artifact_discovery_pages @use_domestic @feature_bot
  Scenario: BrowsePage - Verify product crawled on browse page for price facet in DOMESTIC mode
    Given I visit the web site as a guest user in "domestic" mode
    And I navigate to the "Pumps" browse page under "SHOES"
    Then I should be in category browse page
    And I verify product crawl for "Under $50" item from "Price" facet on left nav

  @domain_discovery @artifact_discovery_pages @use_domestic @feature_bot
  Scenario: BrowsePage - Verify product crawled on browse page for brand facet in DOMESTIC mode
    Given I visit the web site as a guest user in "domestic" mode
    And I navigate to the "Makeup" browse page under "BEAUTY"
    Then I should be in category browse page
    And I verify product crawl for "Clinique" item from "Brand" facet on left nav

  @domain_discovery @artifact_discovery_pages @use_domestic @feature_bot
  Scenario: BrowsePage - Verify product crawled on browse page for size facet in DOMESTIC mode
    Given I visit the web site as a guest user in "domestic" mode
    And I navigate to the "Flats" browse page under "SHOES"
    Then I should be in category browse page
    And I verify product crawl for "5" item from "Size" facet on left nav

  @domain_discovery @artifact_discovery_pages @use_domestic @feature_bot
  Scenario: BrowsePage - Verify product crawled on browse page for customer top rated facet in DOMESTIC mode
    Given I visit the web site as a guest user in "domestic" mode
    And I navigate to the "Earrings" browse page under "JEWELRY"
    Then I should be in category browse page
    And I verify product crawl for "5 stars" item from "Customer Ratings" facet on left nav

  @domain_discovery @artifact_discovery_pages @use_domestic @feature_bot
  Scenario: BrowsePage - Verify product crawled on browse page for special offers facet in DOMESTIC mode
    Given I visit the web site as a guest user in "domestic" mode
    And I navigate to the "Coats" browse page under "JUNIORS"
    Then I should be in category browse page
    And I verify product crawl for "Sales & Discounts" item from "Special Offers" facet on left nav

  @domain_discovery @artifact_discovery_pages @use_domestic @feature_bot
  Scenario: BrowsePage - Verify product crawled on browse page for bops facet in DOMESTIC mode
    Given I visit the web site as a guest user in "domestic" mode
    And I navigate to the "Dress Shirts" browse page under "MEN"
    Then I verify that "Free Pick Up In Store" facet is listed on left nav
    When I select zipcode link in pick-up in-store facet section
    Then I verify that bops overlay is displayed
    When I search for zipcode "94102" in bops change store dialog
    And I close the bops change store dialog
    And I verify product crawl for "random" item from "BOPS" facet on left nav

  @domain_discovery @artifact_discovery_pages @use_registry @feature_bot
  Scenario: BrowsePage - Verify product crawled on browse page for color facet in REGISTRY mode
    Given I visit the web site as a guest user in "registry" mode
    And I navigate to the "Backpacks" browse page under "LUGGAGE"
    Then I should be in category browse page
    And I verify product crawl for "Black" item from "Color" facet on left nav

  @domain_discovery @artifact_discovery_pages @use_registry @feature_bot
  Scenario: BrowsePage - Verify product crawled on browse page for price facet in REGISTRY mode
    Given I visit the web site as a guest user in "registry" mode
    And I navigate to the "Rugs" browse page under "HOME DECOR"
    Then I should be in category browse page
    And I verify product crawl for "Under $50" item from "Price" facet on left nav

  @domain_discovery @artifact_discovery_pages @use_registry @feature_bot
  Scenario: BrowsePage - Verify product crawled on browse page for brand facet in REGISTRY mode
    Given I visit the web site as a guest user in "registry" mode
    And I navigate to the "Cookware & Cookware Sets" browse page under "KITCHEN"
    Then I should be in category browse page
    And I verify product crawl for "Calphalon" item from "Brand" facet on left nav

  @domain_discovery @artifact_discovery_pages @use_registry @feature_bot
  Scenario: BrowsePage - Verify product crawled on browse page for customer top rated facet in REGISTRY mode
    Given I visit the web site as a guest user in "registry" mode
    And I navigate to the "Pillows" browse page under "BED & BATH"
    Then I should be in category browse page
    And I verify product crawl for "5 stars" item from "Customer Ratings" facet on left nav

  @domain_discovery @artifact_discovery_pages @use_registry @feature_bot
  Scenario: BrowsePage - Verify product crawled on browse page for special offers facet in REGISTRY mode
    Given I visit the web site as a guest user in "registry" mode
    And I navigate to the "Sheets" browse page under "BED & BATH"
    Then I should be in category browse page
    And I verify product crawl for "Sales & Discounts" item from "Special Offers" facet on left nav

  @domain_discovery @artifact_discovery_pages @use_reigstry @feature_bot
  Scenario: BrowsePage - Verify product crawled on browse page for bops facet in REGISTRY mode
    Given I visit the web site as a guest user in "registry" mode
    And I navigate to the "Electrics" browse page under "KITCHEN"
    Then I verify that "Free Pick Up In Store" facet is listed on left nav
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
    And I navigate to the "Window Treatments" browse page under "HOME"
    Then I should be in category browse page
    And I verify product crawl for "CHF" item from "Brand" facet on left nav

  @domain_discovery @artifact_discovery_pages @use_iship @feature_bot
  Scenario: BrowsePage - Verify product crawled on browse page for size facet in ISHIP mode
    Given I visit the web site as a guest user in "iship" mode
    And I navigate to the "Flats" browse page under "SHOES"
    Then I should be in category browse page
    And I verify product crawl for "5" item from "Size" facet on left nav

  @domain_discovery @artifact_discovery_pages @use_iship @feature_bot
  Scenario: BrowsePage - Verify product crawled on browse page for customer top rated facet in ISHIP mode
    Given I visit the web site as a guest user in "iship" mode
    And I navigate to the "Earrings" browse page under "JEWELRY"
    Then I should be in category browse page
    And I verify product crawl for "5 stars" item from "Customer Ratings" facet on left nav

  @domain_discovery @artifact_discovery_pages @use_iship @feature_bot
  Scenario: BrowsePage - Verify product crawled on browse page for special offers facet in ISHIP mode
    Given I visit the web site as a guest user in "iship" mode
    And I navigate to the "Coats" browse page under "JUNIORS"
    Then I should be in category browse page
    And I verify product crawl for "Sales & Discounts" item from "Special Offers" facet on left nav
