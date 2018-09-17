Feature:  Verify different S & B functionality

   @dsv_desktop_sev2
  Scenario: In Registry mode - Verify Shop and Brand
    Given I visit the web site as a guest user in "registry" mode
    When I navigate to the "Dinnerware" browse page under "DINING"
    Then I verify registry left facet list

   @dsv_desktop_sev2
  Scenario: Verify buy or shop display in the url for - domestic mode
    Given I visit the web site as a guest user
    When I navigate to the "Activewear" browse page under "KIDS"
    Then I should see the Popular Searches at the bottom
    When I click on any keyword in the Popular Searches
    Then the URL should be in "/buy or /shop" format


   @dsv_desktop_sev2
  Scenario: In Domestic mode - Verify Shop and Brand
    Given I visit the web site as a guest user in "domestic" mode
    When I navigate to "Brands" category page
    Then I verify left nav links of all brands page

   @dsv_desktop_sev2
  Scenario: Verify online cookie
    Given I visit the web site as a guest user
    And I navigate to the sign-in page
    Then I fetch the "macys_online" value & expiration
    When I navigate to "WOMEN" category page
    Then I verify the "macys_online" value & expiration is the same as Sign In page

   @dsv_desktop_sev2
  Scenario: Verify Q&A in PDP and view source - Registry mode
    Given I visit the web site as a guest user in "registry" mode
    When I navigate directly to PDP with PID "83826" in registry mode
    And I scroll down to view olapic panel
    And I click "Product Q&A" in bottom tabs section on member PDP registry mode
    Then I verify "Q&A section" in bottom tabs section on member PDP registry mode
    And I verify Q&A in page source

  Scenario: Verify Static page should be greyed out and should be clickable after clear selection
    Given I visit the web site as a guest user
    When I navigate to the "Makeup" browse page under "BEAUTY"
    And I navigate to "Eyes" sub category from left nav links
    Then I "expand" the "Brand" facet on left nav
    When I select "MAC" item from "Brand" facet on left nav
    Then I should see "MAC" facet value selected is included in the title tag
    When I click on Clear All Selections
    Then I should not see selected facet values in facets selection screen

   @dsv_desktop_sev2
  Scenario: Verify the Dyces functionality
    Given I visit the web site as a guest user
    When I navigate to a product having "available and orderable" attributes
    Then I store product title
    And I click "A2B" button on "member" PDP "site" mode
    Then I should see products in scroller
    And I should see scrolling for recommendation panel
    Then I select any recommendation product and navigate to bag to match products name