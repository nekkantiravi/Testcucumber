Feature: As a mobile user i want to select color for the product i wish to buy

  @dsv_mew_sev1 @domain_selection
  Scenario:  As a mobile user I should see color name changed accordingly when I change colors for a product
    Given I visit the mobile web site as a guest user
    When I search using mobile website for "jeans"
    Then I should be in Search Landing page using mobile website
    When I select color swatch product
    Then I should see the default selected color
    Then I select a random color swatch for the product and verify color name accordingly
