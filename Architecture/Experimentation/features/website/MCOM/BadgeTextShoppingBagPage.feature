Feature: Shopping bag
  Scenario Outline: To verify the final cost appears in browse page
    Given I visit the web site as a <user_type> user
    When I navigate to the "Dresses" browse page under "WOMEN"
    And I select the random product with "ProductFinalCost"
    And I should be product description page with add to bag and add to list button
    Then I verify that product final cost should be same as on browse page
    Examples:
      | user_type  |
      | guest      |
      | registered |

  Scenario Outline: To verify the promo_code appears in browse page
    Given I visit the web site as a <user_type> user
    When I navigate to the "Dresses" browse page under "WOMEN"
    And I select the random product with "ProductPromoCode"
    And I should be product description page with add to bag and add to list button
    Then I verify the "SUMMER" promo_code values should be same as browse page
    Examples:
      | user_type  |
      | guest      |
      #| registered |



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
    And I should be product description page with add to bag and add to list button
    Then I verify the "SUMMER" promo_code values should be same as browse page

    Examples:
      | user_type  |
      | guest      |
      | registered |



  Scenario Outline: To verify select size
    Given I visit the web site as a <user_type> user
    When I navigate to the "Dresses" browse page under "WOMEN"
    And I select a random product in a quickview dialog
    And I click on 'see full product details' link from the quickview dialog
    Then I should be product description page with add to bag and add to list button
    And I select the size of the product
    Examples:
      | user_type  |
      | guest      |
      #| registered |

  Scenario Outline: To verify click on add to bag button
    Given I visit the web site as a <user_type> user
    When I navigate to the "Dresses" browse page under "WOMEN"
    And I select a random member product
    Then I should be product description page with add to bag and add to list button
    And I select the size of the product
    And I click on "ADD TO BAG" button
    Then I should be on ATB page with go back and checkout button
    Examples:
      | user_type  |
      | guest      |
      | registered |

  Scenario Outline: To verify user is able to see "Add to Bag"
    Given I visit the web site as a <user_type> user
    When  I navigate to the "Dresses" browse page under "WOMEN"
    And I select a random member product
    Then I should be product description page with add to bag and add to list button
    Then I select the size of the product
    And I click on "ADD TO BAG" button
    Then I should be on ATB page with go back and checkout button
    And I should see following elements on "add_to_bag" page:
      | header                 |
      | product_name           |
      | color                  |
      | size                   |
      | productImage           |
      | sub_total_price        |
      | item_quantity          |
      | go back                |
      | checkout               |
    Examples:
      | user_type  |
      | guest      |
      | registered |

  Scenario Outline: To verify the selected image should appear in shopping bag page
    Given I visit the web site as a <user_type> user
    When  I navigate to the "Dresses" browse page under "WOMEN"
    And I select a random member product
    Then I should be product description page with add to bag and add to list button
    And I click on "ADD TO BAG" button
    Then I should be on ATB page with go back and checkout button
    And I should be on shopping bag page on clicking checkout button
    Examples:
      | user_type  |
      | guest      |
      | registered |

    Scenario Outline:Verify user is able to "checkout" from "Add to Bag" page
      Given I visit the web site as a <user_type> user
      When  I navigate to the "Dresses" browse page under "WOMEN"
      And I select a random member product
      Then I should be product description page with add to bag and add to list button
      And I click on "ADD TO BAG" button
      Then I should be on ATB page with go back and checkout button
      And I should be on shopping bag page on clicking checkout button
      Then I verify the "your shopping bag" text appears
      And I verify the image should appears on shopping page
      Examples:
        | user_type  |
        | guest      |
        | registered |


