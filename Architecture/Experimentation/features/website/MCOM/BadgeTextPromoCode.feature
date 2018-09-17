Feature: Promo Code


  Scenario Outline: To verify pdp page
    Given I visit the web site as a <user_type> user
    When I navigate to the "Dresses" browse page under "WOMEN"
    And I select a random member product
    Then I should be product description page with add to bag and add to list button
    Examples:
      | user_type  |
      | guest      |
      | registered |

  Scenario Outline: To select the product "size" pdp page
    Given I visit the web site as a <user_type> user
    When I navigate to the "Dresses" browse page under "WOMEN"
    And I select a random member product
    And I should be product description page with add to bag and add to list button
    Then I select the size of the product
    Examples:
      | user_type  |
      | guest      |
      | registered |

  Scenario Outline: To clicking the product "Add to bag"  button on pdp page
    Given I visit the web site as a <user_type> user
    When I navigate to the "Dresses" browse page under "WOMEN"
    And I select a random member product
    And I should be product description page with add to bag and add to list button
    And I select the size of the product
    Then I click on "ADD TO BAG" button
    Examples:
      | user_type  |
      | guest      |
      | registered |

  Scenario Outline: To verify Add to bag page with go back and checkout button
    Given I visit the web site as a <user_type> user
    When I navigate to the "Dresses" browse page under "WOMEN"
    And I select a random member product
    And I should be product description page with add to bag and add to list button
    And  I select the size of the product
    And  I click on "ADD TO BAG" button
    Then I should be on ATB page with go back and checkout button
    Examples:
      | user_type  |
      | guest      |
      | registered |

  Scenario Outline: To clicking on checkout button on Add to bag page
    Given I visit the web site as a <user_type> user
    When I navigate to the "Dresses" browse page under "WOMEN"
    And I select a random member product
    And  I should be product description page with add to bag and add to list button
    And  I select the size of the product
    And  I click on "ADD TO BAG" button
    Then I should be on ATB page with go back and checkout button
    And  I click on checkout button
    Examples:
      | user_type  |
      | guest      |
      #| registered |

  Scenario Outline: To verify your shopping bag page appears
    Given I visit the web site as a <user_type> user
    When I navigate to the "Dress" browse page under "WOMEN"
    And I select a random member product
    And  I should be product description page with add to bag and add to list button
    And  I select the size of the product
    And  I click on "ADD TO BAG" button
    Then I should be on ATB page with go back and checkout button
    And  I should be on shopping bag page on clicking checkout button
    Examples:
      | user_type  |
      | guest      |
      | registered |

  Scenario Outline: To verify promocode
    Given I visit the web site as a <user_type> user
    When I navigate to the "Dresses" browse page under "WOMEN"
    And I select the random product with "ProductPromoCode"
    Then I verify the "SUMMER" promo_code values should be same as browse page
    And I should be product description page with add to bag and add to list button
    And I select the size of the product
    And I click on "ADD TO BAG" button
    Then I should be on ATB page with go back and checkout button
    And I should be on shopping bag page on clicking checkout button
    And I scroll down and go to "My Wallet"
    And I enter "SUMMER" Promocode on Have a Promocode? text field
    And I click on Apply button
    Then I verify "EXTRA" message should appear after applying promocode
    Examples:
      | user_type  |
      | guest      |
      #| registered |