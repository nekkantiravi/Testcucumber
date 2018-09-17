Feature: DSV wishlist features

  @dsv_desktop_sev2
  Scenario: Rendering the My WishList Page as a guest user(for DSV)
    Given I visit the web site as a guest user
    When I select wishlist link in header
    Then I should see wishlist landing page as a guest user
    When I select sign in link from wishlist page
    Then I should see Sign In Page

  @dsv_desktop_sev2
  Scenario: As a user, I should be able to remove product from wishlist - guest
    Given I visit the web site as a guest user
    When I navigate to "JEWELRY" category page
    When I select a random product
    And   I add the product to wishlist
    And   I select wishlist link on the wishlist overlay in PDP page
    Then  I should see wishlist landing page as a guest user
    When  I remove a random product from wishlist page
    Then  I should not see added product in product line items on wishlist page
    And   I should see the selected list name with "0" item count

  @dsv_desktop_sev2
  Scenario: Rendering the My WishList Page as a registered user(for DSV)
    Given I visit the web site as a registered user
    When I select wishlist link in header
    Then I should see wishlist landing page as a registered user
    When I navigate to my account page
    Then I should see "myaccount" page is rendered

  @dsv_desktop_sev2
  Scenario: As a user, I should be able to remove product from wishlist - registered
    Given I visit the web site as a registered user
    And  I delete all lists in wishlist page
    When I add a random product to WishList
    And  I select wishlist link on the wishlist overlay in PDP page
    Then I should see wishlist landing page as a registered user
    When I remove a random product from wishlist page
    Then  I should not see added product in product line items on wishlist page
    And I should see the selected list name with "0" item count