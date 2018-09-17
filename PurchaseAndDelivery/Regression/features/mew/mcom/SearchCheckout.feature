Feature: Verify Search Checkout functionality on mobile site

  @dsv_mew_sev1
  Scenario: Verify order review with a member product as a guest user-Search keyword
    Given I visit the mobile web site as a guest user in domestic mode
    When I search "Dresses" using mobile website
    Then I should be in Search Landing page
    And I select a random member product
    Then I should be redirected to PDP page
    When I add product to my bag from standard PDP Page using mobile site
    When I checkout until I reach the order review page using mobile website as a "guest" user

  @dsv_mew_sev1
  Scenario: Verify order review with a member product as a guest user by navigating back to Home Page from shopping bag
    Given I visit the mobile web site as a guest user in domestic mode
    When I search "Dresses" using mobile website
    Then I should be in Search Landing page
    And I select a random member product
    Then I should be redirected to PDP page
    When I add product to my bag from standard PDP Page using mobile site
    And I navigate back to home page using mobile website
    When I navigate to shopping bag page using mobile website
    Then I checkout until I reach the order review page using mobile website as a "guest" user

  @Mew_UFT @release_17H @project_UFT
  Scenario: verify that the wording is updated on non secure-m sign-in checkout page
    Given I visit the mobile web site as a guest user
    And I directly add an available and orderable product "1494" to my bag in mobile site
    And I click on continue checkout button on shoppping bag page
    Then I should see expected word "account" on sign in checkout page