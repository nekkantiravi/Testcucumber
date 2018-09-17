Feature: Product Description Page

  Scenario Outline:To verify product image on pdp page
      Given I visit the web site as a <user_type> user
      When I navigate to the "Dresses" browse page under "WOMEN"
      And I select the random product with "ProductImage"
      And I should be product description page with add to bag and add to list button
      Then I verify that the product image should be same as on browse page
      Examples:
          | user_type  |
          | guest      |
          | registered |

  Scenario Outline:To verify product name on pdp page
      Given I visit the web site as a <user_type> user
      When I navigate to the "Dresses" browse page under "WOMEN"
      And I select the random product with "ProductName"
      And I should be product description page with add to bag and add to list button
      Then I verify that the product name should be same as on browse page
      Examples:
          | user_type  |
          | guest      |
          | registered |


  Scenario Outline: To verify the final cost appears in pdp page
      Given I visit the web site as a <user_type> user
      When I navigate to the "Dresses" browse page under "WOMEN"
      And I select the random product with "ProductFinalCost"
      And I should be product description page with add to bag and add to list button
      Then I verify that product final cost should be same as on browse page
      Examples:
        | user_type  |
        | guest      |
        | registered |

  Scenario Outline: To verify the promo_code appears in pdp page
      Given I visit the web site as a <user_type> user
      When I navigate to the "Dresses" browse page under "WOMEN"
      And I select the random product with "ProductPromoCode"
      Then I verify the "SUMMER" promo_code values should be same as browse page
      And I should be product description page with add to bag and add to list button
    Examples:
      | user_type  |
      | guest      |
      | registered |

  Scenario Outline: To verify product zoom functionality in pdp page
      Given I visit the web site as a <user_type> user
      When I navigate to the "Dresses" browse page under "WOMEN"
      And I select a random member product
      And I should be product description page with add to bag and add to list button
      Then I verify that the product should zoom after hover on the product
      Examples:
          | user_type  |
          | guest      |
          | registered |

  Scenario Outline: To verify error message appears in pdp page
      Given I visit the web site as a <user_type> user
      When I navigate to the "Dresses" browse page under "Women"
      And I select a random member product
      And I should be product description page with add to bag and add to list button
      And I click on "ADD TO BAG" button
      Then I verify that the error message appears as "please select a size"
      Examples:
        | user_type  |
        | guest      |
        | registered |

