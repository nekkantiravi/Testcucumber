Feature: Test for verify-BOPS

  @dsv_mew_sev1
  Scenario: Verify BOPS checkout functionality
    Given I visit the mobile web site as a guest user
    When I add an "available_bops" product to my bag using mobile website and select checkout
    And I select pick up option for bops item after selecting available store
    Then I should see bops shipping in order summary on shopping bag page
    When I checkout until I reach the shipping page using mobile website as a "bops" user
    Then I should see bops shipping in order summary section
    When I checkout until I reach the payment page using mobile website as a "bops" user
    Then I should see bops shipping in order summary section
    When I checkout until I reach the order review page using mobile website as a "bops" user