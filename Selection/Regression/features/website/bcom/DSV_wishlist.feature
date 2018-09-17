#Created by Gabriel Zanin on 05/25/17


Feature: WishList BCOM

  @bcom_regression @selection @dsv_sev2_dryrun @WIP
  Scenario: As a guest user, I should be able to remove a product from wishlist
    Given I visit the web site as a guest user
    And  I delete all lists in wishlist page
    When I add a random product to WishList
    And  I select wishlist link on the wishlist overlay in PDP page
    Then I should see wishlist landing page as a guest user
    When I remove a random product from wishlist page
    Then I should not see added product in product line items on wishlist page

  @dsv_sev2_dryrun @use_regression @domain_selection
  Scenario: Verify the Added to Wishlist overlay
    Given I visit the web site as a registered user in "registry" mode
    When I select wishlist link in header
    And I delete all lists in wishlist page
    And I create a list "dsv_test" from wishlist page
    When I navigate to a product having "orderable and available" attributes
    And I add the product to wishlist
    Then I should see wishlist landing page as a registered user
    And I delete all lists in wishlist page

