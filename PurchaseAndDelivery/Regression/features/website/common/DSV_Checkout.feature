Feature: DSV Checkout Scenarios

  @dsv_desktop_sev2
  Scenario: Verify profile with email Id having special characters in domain & perform checkout
    Given I visit the web site as a guest user
    When I create a new profile with an email has special characters
    And I add a "orderable and available" product to my bag
    And I checkout until I reach the shipping & payment page as an "signed in" user