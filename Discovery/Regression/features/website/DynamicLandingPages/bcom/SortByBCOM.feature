#Author: Discovery QE
#Date Created: 28/11/2016

Feature: Verify Sort By on DLP

  @use_regression @domain_discovery @mode_registry @mode_iship @mode_domestic
  Scenario Outline: DLP Page - Search for a keyword with results contains sort by drop down in DOMESTIC mode
    Given I am on DynamicLandingPage in "normal" mode with pagination
    And I verify that all the product thumbnails displayed properly on the Dynamic Landing page
    And  I save product order
    When I select "<SortOrder>" in sort by drop down
    Then I should see product display order modified
    And I verify that all the product thumbnails displayed properly on the Dynamic Landing page
    Examples:
      | SortOrder          |
      | Price (low-high)   |
      | Price (high-low)   |
      | Customer Top Rated |
      | New Arrivals       |
      | Best Sellers       |
      | Our Top Picks      |

  @use_regression @domain_discovery @mode_registry @mode_iship @mode_domestic
  Scenario Outline: DLP Page - Search for a keyword with results contains sort by drop down in REGISTRY mode
    Given I am on DynamicLandingPage in "registry" mode with pagination
    And I verify that all the product thumbnails displayed properly on the Dynamic Landing page
    And I save product order
    When I select "<SortOrder>" in sort by drop down
    Then I should see product display order modified
    And I verify that all the product thumbnails displayed properly on the Dynamic Landing page
    Examples:
      | SortOrder          |
      | Price (low-high)   |
      | Price (high-low)   |
      | Customer Top Rated |
      | New Arrivals       |
      | Best Sellers       |
      | Our Top Picks      |

  @use_regression @domain_discovery @mode_registry @mode_iship @mode_domestic
  Scenario Outline: DLP Page - Search for a keyword with results contains sort by drop down in ISHIP mode
    Given I am on DynamicLandingPage in "iship" mode with pagination
    And I verify that all the product thumbnails displayed properly on the Dynamic Landing page
    And I save product order
    When I select "<SortOrder>" in sort by drop down
    Then I should see product display order modified
    And I verify that all the product thumbnails displayed properly on the Dynamic Landing page
    Examples:
      | SortOrder          |
      | Price (low-high)   |
      | Price (high-low)   |
      | Customer Top Rated |
      | New Arrivals       |
      | Best Sellers       |
      | Our Top Picks      |