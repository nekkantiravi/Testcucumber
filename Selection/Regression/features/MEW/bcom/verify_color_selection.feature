Feature: Component tests for Product selection-Color

  @dsv_mew_sev2 @domain_selection
  Scenario: Verify the color functionality for the members of master
    Given I visit the mobile web site as a guest user
    When I search using mobile website for "towels"
    Then I should be in Search Landing page using mobile website
    When I select color swatch product
    Then I should see the default selected color
    And  I should see the relevant product image being displayed
    When I select a random color swatch for the product
    Then I should see the selected color of a product