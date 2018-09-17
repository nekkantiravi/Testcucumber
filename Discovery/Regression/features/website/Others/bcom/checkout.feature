Feature: Verify order review page for BOPS and normal item as a guest user

  @dsv_desktop_sev1 @ifs
  Scenario: Verify checkout for bops and normal item as a Guest user
    Given I visit the web site as a guest user
    When I navigate to the "jeans" browse page under "WOMEN"
    And I add a random product to bag
    And I navigate back to "Home" page
    When I add an "available_bops and available and orderable" product to my bag and select checkout
    And I select pick up option for bops item after selecting a store
    Then I should see bops shipping in order summary on shopping bag page
    When I checkout until I reach the payment page as a "bops" user
    Then I should see bops shipping in order summary section
    When I checkout until I reach the order review page as a "bops" user
    Then I should see bops shipping in order summary section

  @dsv_desktop_sev2
  Scenario: Verify Dyces PROS Recommendations for BCOM
    Given I visit the web site as a guest user in "domestic" mode
    When I select a "available_bops and available and orderable" product to my bag
    Then I should see Customers Also Viewed and You Might Also Like panels
    And I add product from customer also viewed panel
    Then I verify product selected from dyces available in shopping bag page