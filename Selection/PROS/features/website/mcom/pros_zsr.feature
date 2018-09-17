# Author: Discovery QE Team
# Story: B-15214 - MCOM: ZSR Horizontal Enhancements

Feature: Verifying recommendations on all Zero Search Result (ZSR) pages.

  @use_regression @pros @domain_selection
  Scenario Outline: Verify Scrolled Pixel request when ZSR-H panel is loaded in all mode
    Given I am on ZeroSearchResultPage for "lansdlanksd" in <shopping_mode> mode
    Then I verify that recommendation panel is displayed on ZSR page
    When I select left arrow on recommendation panel
    And I should see the "scrolled" informant for "ZSR Horizontal" panel in Dyces logs with the following parameters:
      | visitorId        |
      | context          |
      | customerId       |
      | responseType     |
      | deliveryId       |
      | productPositions |
      | headerId         |
      | controlGroupId   |
    Examples:
      | shopping_mode |
      | domestic      |
      | iship         |
      | registry      |

  @use_regression @pros @domain_selection
  Scenario Outline: Verify Presented Pixel request when ZSR-H panel is loaded in all mode
    Given I am on ZeroSearchResultPage for "lansdlanksd" in <shopping_mode> mode
    Then I verify that recommendation panel is displayed on ZSR page
    And I should see the "PixelPresented" informant for "ZSR Horizontal" panel in Dyces logs with the following parameters:
      | visitorId        |
      | context          |
      | customerId       |
      | responseType     |
      | deliveryId       |
      | productPositions |
      | headerId         |
      | controlGroupId   |
    Examples:
      | shopping_mode |
      | domestic      |
      | iship         |
      | registry      |

#   Site mode

  @use_regression @pros @domain_selection
  Scenario: Verify QuickView button is present for member products in ZSR_ZONE_B recommendation panel in Domestic mode
    Given I am on ZeroSearchResultPage for "jnjnsecjnc" in Domestic mode
    Then I verify that recommendation panel is displayed on ZSR page
    When I quick view any "member" recommended product in the panel on zsr page
    Then I verify that quick peek is displayed
    And I should see the Quick view of the product clicked

  @use_regression @pros @domain_selection
  Scenario: Verify number of recommended items on ZSR_ZONE_B panel
    Given I am on ZeroSearchResultPage for "lkafladnflkas" in Domestic mode
    Then I verify that recommendation panel is displayed on ZSR page
    And I verify that left navigation arrow is disabled
    And I should see up to 5 recommended products in the first set on zsr page
    And I should see up to 25 recommended products on zsr page
    When I navigate to last set of products
    Then I verify that right navigation arrow is disabled

  @pros @use_regression @domain_selection
  Scenario: Verify product thumbnail functionality in Horizontal recommendation panel on ZSR page (SITE)
    Given I am on ZeroSearchResultPage for "lkafladnflkas" in Domestic mode
    Then I verify that recommendation panel is displayed on ZSR page
    When I select any random recommendation product "description" on the panel
    Then I verify that landed on respective product display page

  @pros @use_regression @domain_selection
  Scenario: Verify Quickview for ZSR_ZONE_B recommendation panel
    Given I am on ZeroSearchResultPage for "lkafladnflkas" in Domestic mode
    When I quick view any "member" recommended product in the panel on zsr page
    Then I verify that quick peek is displayed
    And I should see the Quick view of the product clicked

#    Registry mode

  @use_regression @pros @domain_selection
  Scenario: Verify QuickView button is present for member products in ZSR_ZONE_B recommendation panel in Registry mode
    Given I am on ZeroSearchResultPage for "jnjnsecjnc" in Registry mode
    Then I verify that recommendation panel is displayed on ZSR page
    When I quick view any "member" recommended product in the panel on zsr page
    Then I verify that quick peek is displayed
    And I should see the Quick view of the product clicked

  @use_regression @pros @domain_selection
  Scenario: Verify number of recommended items on ZSR_ZONE_B panel (registry)
    Given I am on ZeroSearchResultPage for "lkafladnflkas" in Registry mode
    Then I verify that recommendation panel is displayed on ZSR page
    And I verify that left navigation arrow is disabled
    And I should see up to 5 recommended products in the first set on zsr page
    And I should see up to 25 recommended products on zsr page
    When I navigate to last set of products
    Then I verify that right navigation arrow is disabled

#iShip mode

  @use_regression @pros @domain_selection
  Scenario: Verify QuickView button is present for member products in ZSR_ZONE_B recommendation panel in Iship mode
    Given I am on ZeroSearchResultPage for "jnjnsecjnc" in Iship mode
    Then I verify that recommendation panel is displayed on ZSR page
    When I quick view any "member" recommended product in the panel on zsr page
    Then I verify that quick peek is displayed
    And I should see the Quick view of the product clicked

  @use_regression @pros @domain_selection
  Scenario: Verify number of recommended items on ZSR_ZONE_B panel (ISHIP)
    Given I am on ZeroSearchResultPage for "lkafladnflkas" in Iship mode
    Then I verify that recommendation panel is displayed on ZSR page
    And I verify that left navigation arrow is disabled
    And I should see up to 5 recommended products in the first set on zsr page
    And I should see up to 25 recommended products on zsr page
    When I navigate to last set of products
    Then I verify that right navigation arrow is disabled

  @pros @use_regression @domain_selection
  Scenario: Verify product thumbnail navigated from horizontal PROS panel on ZSR page as iShip guest
    Given I am on ZeroSearchResultPage for "lkafladnflkas" in Iship mode
    Then I verify that recommendation panel is displayed on ZSR page
    When I select any random recommendation product "thumbnail" on the panel
    Then I verify that landed on respective product display page
