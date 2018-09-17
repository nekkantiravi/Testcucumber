Feature: I want to see available colors for a product

  @dsv_mew_sev2 @domain_selection
  Scenario: Verify the default selected color
    Given I visit the mobile web site as a guest user
    When I search using mobile website for "sandals"
    Then I should be in Search Landing page using mobile website
    When I select color swatch product
    Then I should see the default selected color

  @dsv_mew_sev2 @domain_selection
  Scenario: Verify the color selection on pdp page
    Given I visit the mobile web site as a guest user
    When I search using mobile website for "sandals"
    Then I should be in Search Landing page using mobile website
    When I select color swatch product
    Then I should see the selected color of a product