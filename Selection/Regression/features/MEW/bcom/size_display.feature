Feature: Product Selection - Size Display

  @dsv_mew_sev2 @domain_selection
  Scenario: Verify sizes that are unavailable for default select color should be displayed as inactive
    Given I visit the mobile web site as a guest user
    When I search using mobile website for "sandals"
    Then I should be in Search Landing page using mobile website
    When I select color swatch product
    Then I should see unavailable sizes are inactive for the default color

  @dsv_mew_sev2 @domain_selection
  Scenario: Verify sizing should be normalized for member of master product
    Given I visit the mobile web site as a guest user
    When I search using mobile website for "swimsuits"
    Then I should be in Search Landing page using mobile website
    When I select a random master product using mobile website
    Then I should see size name "Small" as "S" on size swatch for member of master pdp
    And I should see size name "Large" as "L" on size swatch for member of master pdp