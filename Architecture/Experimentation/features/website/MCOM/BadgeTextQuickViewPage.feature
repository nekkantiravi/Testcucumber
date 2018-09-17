Feature: Quick View Functionality

  Scenario Outline: To verify Quick view
    Given I visit the web site as a <user_type> user
    When I navigate to the "Dresses" browse page under "WOMEN"
    When I select a random product in a quickview dialog
    Examples:
      | user_type  |
      | guest      |
      #| registered |

  Scenario Outline: To verify redirect to Quick view Page
    Given I visit the web site as a <user_type> user
    When I navigate to the "Dresses" browse page under "WOMEN"
    When I select a random product in a quickview dialog
    And I should be quick view page with add to bag and add to list button
    Examples:
      | user_type  |
      | guest      |
      #| registered |

  Scenario Outline: To verify the image
    Given I visit the web site as a <user_type> user
    When I navigate to the "Dresses" browse page under "WOMEN"
    When I select the random product in a quickview dialog with "ProductImage"
    And I should be quick view page with add to bag and add to list button
    Then I verify that the product image on quickPage should be same as on browse page
    Examples:
      | user_type  |
      | guest      |
      #| registered |

  Scenario Outline: To verify the title
    Given I visit the web site as a <user_type> user
    When I navigate to the "Dresses" browse page under "WOMEN"
    When I select the random product in a quickview dialog with "ProductName"
    And I should be quick view page with add to bag and add to list button
    Then I verify that the product name on quickPage should be same as on browse page
    Examples:
      | user_type  |
      | guest      |
      #| registered |

  Scenario Outline: To verify the sale price
    Given I visit the web site as a <user_type> user
    When I navigate to the "Dresses" browse page under "WOMEN"
    When I select the random product in a quickview dialog with "ProductSalePrice"
    And I should be quick view page with add to bag and add to list button
    Then I verify that the product sale price on quickPage should be same as on browse page
    Examples:
      | user_type  |
      | guest      |
      #| registered |

  Scenario Outline: To verify the final cost appears
    Given I visit the web site as a <user_type> user
    When I navigate to the "Dresses" browse page under "WOMEN"
    When I select the random product in a quickview dialog with "ProductFinalCost"
    And I should be quick view page with add to bag and add to list button
    Then I verify that the Product final cost on quickPage should be same as on browse page
    Examples:
      | user_type  |
      | guest      |
      #| registered |

  Scenario Outline: To verify the promo_code
    Given I visit the web site as a <user_type> user
    When I navigate to the "Dresses" browse page under "WOMEN"
    When I select the random product in a quickview dialog with "ProductPromoCode"
    #And I should be quick view page with add to bag and add to list button
    Then I verify that the "SUMMER" promo_code value on quickPage should be same as on browse page
    Examples:
      | user_type  |
      | guest      |
      | registered |

  Scenario Outline: To verify the see full product details link
    Given I visit the web site as a <user_type> user
    When I navigate to the "Dresses" browse page under "WOMEN"
    When I select a random product in a quickview dialog
    And I should be quick view page with add to bag and add to list button
    Then I verify that the "see full product details" link appears
    Examples:
      | user_type  |
      | guest      |
      #| registered |

