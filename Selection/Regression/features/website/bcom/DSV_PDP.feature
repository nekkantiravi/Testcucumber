#Created by Gabriel Zanin on 05/25/17

Feature: PDP Bcom

  @bcom_regression @selection @dsv_desktop_sev2
  Scenario:Verify the write a comment link is present in domestic mode only
    Given I visit the web site as a guest user
    When I navigate directly to Reviews PDP page
    And I click on the "Reviews" tab
    Then I should see product ratings and reviews under the reviews tab on PDP Page
    And I "should" see "write a comment" link
    When I navigate to international context page
    And I change the country to "Canada"
    And I click on the "Reviews" tab
    Then I "should not" see "write a comment" link

  @bcom_regression @selection @dsv_desktop_sev2
  Scenario: Verify another E-gift card to bag
    Given I visit the web site as a guest user
    When I add a random E-Gift gift card to my bag
    And I navigate to PDP from shopping bag
    And I add another product to my bag
    And I continue to shopping bag
    And I verify the quantity in shopping bag equals 2

  @bcom_regression @selection @dsv_desktop_sev2
  Scenario: Verify social icons in COACH, CHANEL & Gift Cards
    Given I visit the web site as a guest user
    When I navigate to COACH PDP page
    Then I should see social media icons such as facebook, google plus, pinterest and twitter on PDP Page
    When I navigate to CHANEL PDP page
    Then I should not see social media icons such as facebook, google plus, pinterest and twitter on PDP Page
    When I navigate to Gift Card PDP page
    Then I should not see social media icons such as facebook, google plus, pinterest and twitter on PDP Page

  @dsv_desktop_sev2
  Scenario: Verify Zoom functionality in PDP
    Given I visit the web site as a guest user
    When I navigate to a random product
    Then I verify the zoom functionality
    
  @use_regression @domain_selection
  Scenario: Verify product name in browse page is displayed in PDP page
    Given I visit the web site as a guest user
    When I navigate to "SHOES" category page
    And I select "Boots" category from left nav
    Then I verify the product name in browse page continues to pdp

  @dsv_desktop_sev2
  Scenario: As a guest user, I should be able to remove a product from wishlist
    Given I visit the web site as a guest user
    And  I delete all lists in wishlist page
    When I add a random product to WishList
    And  I select wishlist link on the wishlist overlay in PDP page
    Then I should see wishlist landing page as a guest user
    When I remove a random product from wishlist page
    Then I should not see added product in product line items on wishlist page

  @dsv_desktop_sev2
  Scenario: Verify the Added to Wishlist overlay
    Given I visit the website as a registered user with registry
    When I select wishlist link in header
    And I delete all lists in wishlist page
    And I create a list "dsv_test" from wishlist page
    When I navigate to a product having "orderable and available" attributes
    And I add the product to wishlist
    Then I should see wishlist landing page as a registered user
    And I delete all lists in wishlist page