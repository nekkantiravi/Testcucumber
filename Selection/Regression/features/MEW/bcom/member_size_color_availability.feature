Feature: As a customer, I want to be informed if a product is not available in my combined color and size or type preferences.

  @dsv_mew_sev2 @domain_selection
  Scenario: Verify availability of sizes for selected color on member PDP
    Given I visit the mobile web site as a guest user
    When I search using mobile website for "sandals"
    Then I should be in Search Landing page using mobile website
    When I select color swatch product
    Then I should see the availability of sizes for selected color

  @dsv_mew_sev2 @domain_selection
  Scenario: Verify availability of colors for a selected size on member PDP
    Given I visit the mobile web site as a guest user
    When I search using mobile website for "sandals"
    Then I should be in Search Landing page using mobile website
    When I select color swatch product
    Then I should see the availability of colors for selected size
