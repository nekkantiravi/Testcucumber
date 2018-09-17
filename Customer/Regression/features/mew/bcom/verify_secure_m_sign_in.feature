Feature: We want to make sure that m-secure sign in is validated

  @dsv_mew_sev2 @domain_customer
  Scenario: Signing in through secure-m API call
    Given I visit the mobile web site as a guest user in domestic mode
    When I sign in to my existing profile using mobile website

  @dsv_mew_sev2 @domain_customer
  Scenario: Logging out should be clean
    Given I visit the mobile web site as a guest user in domestic mode
    When I sign in to my existing profile using mobile website
    And I sign out from my current mobile site profile

