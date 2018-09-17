Feature: Component tests for Beauty

  @dsv_mew_sev2 @domain_selection
  Scenario: Verify the color name should be displayed in dropdown on the beauty pdp page
    Given I visit the mobile web site as a guest user
    When I search using mobile website for "beauty"
    Then I should be in Search Landing page using mobile website
    When I select beauty product with more than one color
    Then I should see the default selected color name of beauty product
    And  I should see the relevant product image being displayed
    When I select a random beauty color name from dropdown
    Then I should see the selected random color name display in dropdown