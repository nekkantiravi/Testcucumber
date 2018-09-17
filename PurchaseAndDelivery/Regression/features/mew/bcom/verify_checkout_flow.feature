Feature: Checkout flow

  @dsv_mew_sev1 @domain_purchaseanddelivery
  Scenario: Guest user checkout flow
    Given I visit the mobile web site as a guest user
    When I search using mobile website for "jeans"
    Then I should be in Search Landing page using mobile website
    When I select a random member product using mobile website
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
    When I select a random member product using mobile website
    Then I should be redirected to PDP page
    And I add product to my bag from standard PDP Page using mobile site
    When I checkout until I reach the shipping & payment page using mobile website as a "responsive_signed in" user
    Then I should be on responsive checkout page

  @iship @domain_purchase_and_delivery @dsv_mew_sev1
  Scenario: Verify Checkout for iship on MEW, shippingCountry and currency cookie are set by Akamai
    Given I goto Mobile Home page
    And I add the "shippingCountry" cookie value as "AU"
    And I add the "currency" cookie value as "AUD"
    And I search for "3048"
    And I add product to my bag from standard PDP Page using mobile site
    When I navigate to shopping bag page from add to bag page using mobile website
    And I navigate to envoy checkout from shopping bag page
