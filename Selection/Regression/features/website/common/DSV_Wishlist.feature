Feature: DSV wishlist features

  @dsv_desktop_sev2 @domain_selection @use_regression  @dsv_desktop_sev2
  Scenario Outline: As a user, I should be able to add a product to shopping bag and checkout from wishlist
    Given I visit the web site as a <user_type> user
    And I delete all lists in wishlist page
    And I remove all items from the shopping bag
    When I add a random product to WishList
    And I select wishlist link on the wishlist overlay in PDP page
    Then I should see wishlist landing page as a <user_type> user
    When I add product to my bag from wishlist page and continue shopping
    Then I should see wishlist landing page as a <user_type> user
    When I add product to my bag from wishlist page and checkout
    Then I should see the "shopping bag" page
    Examples:
      |user_type    |
     |guest        |
     |registered   |