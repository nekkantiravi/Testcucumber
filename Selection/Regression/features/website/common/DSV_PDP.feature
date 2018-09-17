Feature: PDP Common scenarios

  @dsv_desktop_sev2 @use_regression @domain_selection
  Scenario: Verify Exclusion message on PDP & shopping bag
    Given I visit the web site as a guest user
    When I add a "iship_unavailable" product to my bag and checkout
    When I navigate to international context page
    And I change country to "Australia" and stay on the current page
    And I close the welcome mat if it's visible
    When I navigate to shopping bag page
    Then I should see the item level error:
      | BCOM | This item cannot be shipped to Australia. Please remove it from your bag to proceed. |
    When I navigate to the PDP page from shopping bag page
    Then I should see the item level error on PDP page:
      | BCOM | This item is currently unavailable. Please check back later. |

    #Then an unavailability error message should be displayed on PDP Page


  @dsv_desktop_sev2 @use_regression @domain_selection @dsv_desktop_sev1  @dsv_desktop_sev2
  Scenario: BOPS - Map functionality
    Given I visit the web site as a guest user
    When I navigate to a product having "orderable and available_bops" attributes
    And I select a UPC of the product
    And I navigate to CHOOSE A STORE overlay in PDP
    And I search for zipcode "10022" in bops change store dialog
    And I select a BOPS available store from the ISA overlay
    And I select a view map link for a "bops_store" in ISA overlay
    Then I should see google map displaying for the selected "bops_store"

  @use_regression @domain_selection  @dsv_desktop_sev2 @akamai_waf
  Scenario: Verify PROS vertical recommendations on PDP
    Given I visit the web site as a guest user
    When I navigate PDP page that has recommendations in "vertical" panel in "site" mode
    Then I should see "vertical" recommendation panel on pdp page
    When I select a random product from "vertical" recommendation panel
    Then I should be redirected to PDP page

  @dsv_desktop_sev2  @dsv_desktop_sev2 @akamai_waf
  Scenario: Verify product name in browse page is displayed correctly on PDP page
    Given I visit the web site as a guest user
    When I navigate to the "Dress Shirts" browse page under "MEN"
    Then I verify the product name in browse page continues to PDP

  @dsv_desktop_sev2  @dsv_desktop_sev2 @akamai_waf
  Scenario: Verify Title tag is displayed for PDP Page
    Given I visit the web site as a guest user
    When I navigate to a product having "available and orderable" attributes
    Then I verify the title tag on PDP page

  @dsv_desktop_sev2  @dsv_desktop_sev2 @akamai_waf
  Scenario: Verify social networking links on pdp
    Given I visit the web site as a guest user
    When I search for "nike women"
    Then I should be in Search Landing page
    And I select a random member product
    Then I should be redirected to PDP page
    Then I verify social icon links on PDP

    #Notes:
    #Pinterest, facebook, Twitter links for BCOM
    #Pinterest for MCOM