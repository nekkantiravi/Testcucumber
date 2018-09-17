Feature: MCOM Checkout Scenarios

  @artifact_shopapp @mode_domestic @release_17G @priority_medium @domain_purchaseanddelivery @project_UFT
  Scenario: Verify Guest RC page displaying incorrect information when Express Paypal is selected
    Given I visit the web site as a guest user
    When I add a "orderable and available and prod_available" product to my bag
    And I select checkout with paypal
    And I login into paypal account
    And I cancel and return to shopping bag page from Paypal review page
    And I select checkout with paypal
    And I continue checkout from paypal review page
    Then I should see Paypal option is selected as payment type


  @artifact_shopapp @mode_domestic @release_17G @priority_medium @domain_purchaseanddelivery @project_UFT
  Scenario: Verify Signed RC page displaying incorrect information when Express Paypal is selected
    Given I visit the web site as a registered user
    When I add an "available and orderable and prod_available" product to my bag
    And I select checkout with paypal
    And I login into paypal account
    And I cancel and return to shopping bag page from Paypal review page
    And I select checkout with paypal
    And I continue checkout from paypal review page
    Then I should see Paypal option is selected as payment type

  @dsv_desktop_sev1
  Scenario: Verify checkout as a registry user after adding registry item from BVR page
    Given I visit the web site as a guest user in "domestic" mode
    When I click on "sign in" link in the header
    And I sign in to my existing registry production profile
    And I remove all items from the shopping bag
    When I add "registrable and orderable" product to my bag from BVR page
    And I checkout until I reach the order confirmation page as an "signed in" user

    #Notes: using existing registry credentials as registry creation is prohibited on production

  @dsv_desktop_sev1
  Scenario: Verify sign in during checkout
    Given I visit the web site as a guest user
    When I add a "orderable and available and prod_available" product to my bag
    Then I checkout on add to bag overlay
    When I  click on continue checkout button on shoppping bag page
    And I sign in to my existing registry production profile
    And I continue checkout until I reach the order confirmation page as an "signed" user



