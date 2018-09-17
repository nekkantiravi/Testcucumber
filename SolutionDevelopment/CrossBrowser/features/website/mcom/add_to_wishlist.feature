Feature: Chanel product - Verify the functionality for add to list

  @scenario1 @domain_selection @xbrowser @xbrowser_one
  Scenario Outline: As a user, I should add CHANEL product to wishlist from PDP
    Given I visit the web site as a <user_type> user
    When I navigate to the "CHANEL" browse page under "BEAUTY"
    Then I should be on "CHANEL" subsplash page
    When I select "MAKEUP" in the subsplash page
    And I select "LIPS" facet listed on left nav
    And I select a random chanel product from browse page
    Then I should be redirected to PDP page
    When I add the product to wishlist
    And I select wishlist link on the wishlist overlay in PDP page
    Then I should see wishlist landing page as a <user_type> user
    And I should see added product in product line items on wishlist page

    Examples:
      | user_type  |
      | guest      |
      | registered |