Feature: Browse Page

  Scenario Outline: To verify the final cost appears in browse page
    Given I visit the web site as a <user_type> user
    When I navigate to the "Dresses" browse page under "WOMEN"
    And I select the random product with "ProductFinalCost"
    Then I verify that product final cost should be same as on browse page
    Examples:
      | user_type  |
      | guest      |
      | registered |

  Scenario Outline: To verify the promo_code appears in browse page
      Given I visit the web site as a <user_type> user
      When I navigate to the "Dresses" browse page under "WOMEN"
      And I select the random product with "ProductPromoCode"
      Then I verify the "SUMMER" promo_code values should be same as browse page
      Examples:
      | user_type  |
      | guest      |
      | registered |




