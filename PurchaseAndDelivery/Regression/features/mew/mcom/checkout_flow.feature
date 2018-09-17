Feature: Checkout flow

  @dsv_mew_sev1 @domain_purchaseanddelivery
  Scenario: Guest user checkout flow
    Given I visit the mobile web site as a guest user
    When I search using mobile website for "jeans"
    Then I should be in Search Landing page using mobile website
    When I select a random member product
    Then I should be redirected to PDP page
    And I add product to my bag from standard PDP Page using mobile site
    And I checkout until I reach the shipping page using mobile website as a "guest" user
    And I checkout until I reach the payment page using mobile website as a "guest" user
    When I checkout until I reach the order review page using mobile website as a "guest" user
    Then I should be on responsive checkout page

  @dsv_mew_sev1 @domain_purchaseanddelivery
  Scenario: Signed in user checkout flow
    Given I visit the mobile web site as a registered user
    When I search using mobile website for "jeans"
    Then I should be in Search Landing page using mobile website
    When I select a random member product
    Then I should be redirected to PDP page
    And I add product to my bag from standard PDP Page using mobile site
    And I checkout until I reach the shipping & payment page using mobile website as a "responsive_signed in" user
    Then I should be on responsive checkout page