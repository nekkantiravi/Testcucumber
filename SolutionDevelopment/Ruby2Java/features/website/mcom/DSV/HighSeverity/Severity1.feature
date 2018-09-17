#Author: Solution Development Team
#Date Created: 04/17/2017

Feature: MCOM DSV High Severity Verification Scenarios

  @mcom_dsv_sev1 @scenario1
  Scenario: BrowsePage - Verify the sort by option
    Given I am on Browse Page for "Jeans" under "MEN"
    And   I save product order
    And   I select "Price: High to Low" in sort by drop down
    Then  I should see product display order modified

  @mcom_dsv_sev1 @scenario2 @dsv_desktop_sev1
  Scenario: Verify Search Registry
    Given I visit the web site as a guest user
    When  I visit the registry home page
    And   I search for "Dining" in registry mode
    Then  I verify the display of registry search result page

  @mcom_dsv_sev1 @scenario3
  Scenario: BrowsePage - Verify clear and clear all after selecting multi Facets in DOMESTIC mode
    Given I visit the web site as a guest user
    When  I navigate to the "Jeans" browse page under "MEN"
    And   I select 2 color in the "Color" facet
    And   I select "single" facet value from "Size" facet section
    And   I click on clear all button
    Then  I verify that clear all button is not displayed

  @mcom_dsv_sev1 @scenario4
  Scenario: BrowsePage - Verify multi Facet functionality in DOMESTIC mode
    Given I visit the web site as a guest user
    When  I navigate to the "Jeans" browse page under "MEN"
    And   I select 2 brand in the "Brand" facet
    And   I select 1 facets in the "Price" facet
    And   I select 1 color in the "Color" facet
    And   I click on clear all button
    Then  I verify that clear all button is not displayed

  @mcom_dsv_sev1 @scenario5 @dsv_desktop_sev2
  Scenario: Verify the ability to view Account summary from International mode
    Given I visit the web site as a guest user
    When  I click on signIn link
    Then  SignIn page should get loaded
    When  I sign in to my existing production profile
    And   I navigate to international context page
    And   I change country to "Canada"
    And   I close the welcome mat if it's visible
    And   I navigate to random category splash page
    When  I click on "my account" link in the header
    Then  I should be navigated to My Account Page

  @mcom_dsv_sev1 @scenario6
  Scenario: Verify Change country link
    Given I visit the web site as a guest user
    And   I click on signIn link
    And   SignIn page should get loaded
    When  I sign in to my existing production profile
    When  I navigate to international context page
    Then  I verify the international context page

  @mcom_dsv_sev1 @scenario7
  Scenario: Verify PROS vertical recommendations on Member PDP iShip mode
    Given I visit the web site as a guest user
    And   I navigate to international context page
    And   I change country to "a random country"
    And   I close the welcome mat if it's visible
    When  I search for "lenox"
    And   I navigate to PDP of the first master product
    Then  I should see "vertical" recommendation panel on pdp page
    When  I select a random product from "vertical" recommendation panel
    Then  I should be redirected to PDP page

  @mcom_dsv_sev1 @scenario8 @dsv_desktop_sev2
  Scenario: Verify Find a Store
    Given I visit the web site as a guest user
    When  I navigate to the "Store Locations & Hours" in our store pages
    Then  I verify the "Store Locations & Hours" our store page has rendered
    And   I verify the functionality in "Store Locations & Hours" page

  @mcom_dsv_sev1 @scenario9
  Scenario: As a user, I should be able to remove product from wishlist - guest
    Given I visit the web site as a guest user
    And   I navigate to a random product
    And   I add the product to wishlist
    And   I select wishlist link on the wishlist overlay in PDP page
    Then  I should see wishlist landing page as a guest user
    When  I remove a random product from wishlist page
    Then  I should not see added product in product line items on wishlist page
    And   I should see the selected list name with "0" item count

  @mcom_dsv_sev1 @scenario10  @dsv_desktop_sev2 @selection_domain
  Scenario: Wish list - Click to call should display for furniture product
    Given I visit the web site as a guest user
    When  I click on signIn link
    Then  SignIn page should get loaded
    When  I sign in to my existing production profile
    And   I search for "Couches & Sofas"
    And   I select random product from thumbnail grid
    And   I select sofa product related attributes from PDP
    And   I add the product to wishlist
    And   I select wishlist link on the wishlist overlay in PDP page
    Then  I should see click to call message

  @use_regression @project_registry @use_dsv @prod @mcom_dsv_sev1
  Scenario: Verify Registry home page with the pre production data
    Given I visit the web site as a guest user
    When I visit the registry home page
    Then I verify Registry home page with the pre production data

  @use_dsv @rc_2 @use_regression @release_15D @feature_shipping_section @artifact_shopapp @priority_high @project_responsive_checkout @domain_purchase_and_delivery @mcom_rc_guest @checkout @mcom_dsv_sev1
  Scenario: Verify premium or express shipping on given address in RC shipping section
    Given I visit the web site as a guest user
    When I add a "prod_available and orderable" product to my bag
    And I checkout until I reach the shipping page as a "guest" user
    And I select "express" shipping method
    And I continue checkout entering premium ineligible shipping address
    Then I should see a special error message "We're sorry, we are unable to expedite shipments to the address selected. Please select Standard shipping or provide an alternate shipping address." is displayed

  @use_regression @project_registry @use_dsv @prod @mcom_dsv_sev1
  Scenario: As a guest user, I verify links in registry home page
    Given I visit the web site as a guest user
    When I navigate to registry home page
    Then I should be navigated to registry "Home" page
    And I should see expected media types on registry home page
      | ROW_TYPE | MCOM                                    |
      | 101      | IMAGE, IMAGE_MAP, JSP                   |
      | 103      | IMAGE, IMAGE_MAP, JSP, SLIDESHOW, VIDEO |
      | 106      | CATEGORY_ICON, image                    |
    And I should see and able to click view ads in "row_103_3" row on registry home page
      | COLUMN_ID    | MCOM                           |
      | row3_column1 | /social/registry-star-rewards/ |
      | row3_column2 | /social/registry-gift/         |
      | row3_column3 | /social/registry-events/       |