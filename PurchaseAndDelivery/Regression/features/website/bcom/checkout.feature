Feature: BCOM Checkout Scenarios

  @artifact_shopapp @mode_domestic @release_17G @priority_medium @domain_purchaseanddelivery @project_UFT
  Scenario: Verify Guest RC page displaying incorrect information when Express Paypal is selected
    Given I visit the web site as a guest user
    When I add a "orderable and available and loyalty_pppc and iship_eligible" product to my bag
    And I select checkout with paypal
    And I login into paypal account
    And I cancel and return to shopping bag page from Paypal review page
    And I select checkout with paypal
    And I continue checkout from paypal review page
    Then I should see Paypal option is selected as payment type


  @artifact_shopapp @mode_domestic @release_17G @priority_medium @domain_purchaseanddelivery @project_UFT
  Scenario: Verify Singed RC page displaying incorrect information when Express Paypal is selected
    Given I visit the web site as a registered user
    When I add an "available and orderable and loyalty_pppc and iship_eligible" product to my bag
    And I select checkout with paypal
    And I login into paypal account
    And I cancel and return to shopping bag page from Paypal review page
    And I select checkout with paypal
    And I continue checkout from paypal review page
    Then I should see Paypal option is selected as payment type