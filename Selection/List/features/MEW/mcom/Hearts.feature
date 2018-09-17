Feature: Hearts on MEW Validation & Verification

##HEART Scenarios on pdp page -- START --
  @22 @use_regression @domain_selection @project_MCOM
  Scenario Outline: As a Guest user I should heart a product to wishlist from BROWSE Page in site mode
    Given I visit the mobile web site as a guest user in domestic mode
    Then I navigate to mew "browse" page by url "<url>"
    And I click HEART icon to add product to list
    When I navigate to list page on MEW
    Then I verify the basic components of landingpage for "Guest" user
   Then I verify "1" items and "4" place holders with "Item"
    When I click on Product and verify the Product Details
    Then I navigate to mew "browse" page by url "<url>"
    And I click UNHEART icon to add product to list
    When I navigate to list page on MEW
    Then I verify the basic components of landingpage for "Guest" user
    Then I verify "0" items and "6" place holders with "NoItem"
    Examples:
  | url                                                  |
  | /shop/womens-clothing/womens-jeans?id=3111           |
#  | /shop/beauty/estee-lauder-skin-care-products?id=7657 |




##HEART Scenarios on pdp page -- START --
  @21 @use_regression @domain_selection @project_MCOM
  Scenario Outline: As a Regsitered user I should unheart a product to wishlist from BROWSE Page in site mode
    Given I visit the mobile web site as a registered user
    Then I navigate to mew "browse" page by url "<url>"
    And I click HEART icon to add product to list
    When I navigate to list page on MEW
    Then I verify the basic components of landingpage for "Signedin" user
    Examples:
      | url                                                  |
      | /shop/womens-clothing/womens-jeans?id=3111           |
#  | /shop/beauty/estee-lauder-skin-care-products?id=7657 |
