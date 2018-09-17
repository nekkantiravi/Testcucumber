#Author: Discovery team

Feature: As a product owner,I would like to have Last Act under Price Type 8 appear as badge text for products in search/browse pages

  #### Browse page #####
  @use_regression @domain_discovery @mode_domestic @priority_high @snbc_comp
  Scenario Outline: BrowsePage - Verify that the LAST ACT badge text is displayed in All mode
    Given I am on CategoryBrowsePage for "9915" category id in <shopping_mode> mode
    And I verify that the product count is displayed
    When I select "Last Act" item from "Special Offers" facet on left nav
    Then I verify that products are filtered as per selected facet values
    And I verify the "LAST ACT" badge text is displayed in the product thumbnail on "browse" page
    When I select the quick peek of random product
    Then I verify that quick peek is displayed
    Then I verify the "LAST ACT" badge text is displayed in product QuickView
    Examples:
      | shopping_mode |
      | Domestic      |
      | Registry      |
      | Iship         |