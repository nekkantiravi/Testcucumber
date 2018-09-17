# Author: Discovery QE Team
# Story: B-15214 - MCOM: ZSR Horizontal Enhancements

Feature: As MCOM Business, I would like to enhance existing PROS ZONE UI to a better look and feel.

  @15E @priority_medium @use_regression @pros @mcom @D-25215 @domain_selection
  Scenario: Verify number of recommended items on ZSR_ZONE_B panel
    Given I am on ZeroSearchResultPage for "lkafladnflkas" in Domestic mode
    Then I verify that recommendation panel is displayed on ZSR page
    And I verify that left navigation arrow is disabled
    And I should see up to 5 recommended products in the first set on zsr page
    And I should see up to 25 recommended products on zsr page
    When I navigate to last set of products
    Then I verify that right navigation arrow is disabled

  @15E @use_regression @pros @mcom @D-25215 @domain_selection
  Scenario: Verify number of recommended items on ZSR_ZONE_B panel (registry)
    Given I am on ZeroSearchResultPage for "lkafladnflkas" in Registry mode
    Then I verify that recommendation panel is displayed on ZSR page
    And I verify that left navigation arrow is disabled
    And I should see up to 5 recommended products in the first set on zsr page
    And I should see up to 25 recommended products on zsr page
    When I navigate to last set of products
    Then I verify that right navigation arrow is disabled

  @15E @priority_medium @use_regression @pros @mcom @D-25215 @domain_selection
  Scenario: Verify number of recommended items on ZSR_ZONE_B panel (ISHIP)
    Given I am on ZeroSearchResultPage for "lkafladnflkas" in Iship mode
    Then I verify that recommendation panel is displayed on ZSR page
    And I verify that left navigation arrow is disabled
    And I should see up to 5 recommended products in the first set on zsr page
    And I should see up to 25 recommended products on zsr page
    When I navigate to last set of products
    Then I verify that right navigation arrow is disabled

  @14K @artifact_navapp @pros @mcom @use_regression @domain_selection
  Scenario: Verify product thumbnail functionality in Horizontal recommendation panel on ZSR page (SITE)
    Given I am on ZeroSearchResultPage for "lkafladnflkas" in Domestic mode
    Then I verify that recommendation panel is displayed on ZSR page
    When I select any random recommendation product "description" on the panel
    Then I verify that landed on respective product display page

  @14K @artifact_navapp @pros @mcom @use_regression @domain_selection
  Scenario: Verify Quickview for ZSR_ZONE_B recommendation panel
    Given I am on ZeroSearchResultPage for "lkafladnflkas" in Domestic mode
    When I quick view any "member" recommended product in the panel on zsr page
    Then I verify that quick peek is displayed
    And I should see the Quick view of the product clicked

  @pros @use_regression @domain_selection
  Scenario: Verify product thumbnail navigated from horizontal PROS panel on ZSR page as iShip guest
    Given I am on ZeroSearchResultPage for "lkafladnflkas" in Iship mode
    Then I verify that recommendation panel is displayed on ZSR page
    When I select any random recommendation product "thumbnail" on the panel
    Then I verify that landed on respective product display page
