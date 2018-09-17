Feature: Add to Registry flows

  @dsv_mew_sev1 @domain_selection
  Scenario: Verify 'Add to Registry' flow from registry PDP
    Given I visit the mobile web site as a registry user
    And I navigate back to home page using mobile website
    And I search using mobile website for "cookware"
    Then I should be in Search Landing page using mobile website
    And I select a random member product using mobile website
    And I add product to my registry from standard PDP Page using mobile site
