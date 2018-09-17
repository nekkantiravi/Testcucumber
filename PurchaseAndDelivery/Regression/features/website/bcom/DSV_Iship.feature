Feature:  Iship Functionality
  @dsv_desktop_sev2
  Scenario: Verify country currency
    Given I visit the web site as a guest user
    When I navigate to international context page
    When I change country to "Australia"
    And I close the welcome mat if it's visible
    Then I verify the basic attributes of Iship Home page
    When I navigate to random category browse page
    Then I verify the currency is AUD on the category browse page
    When I navigate to a product having "iship_eligible and available and orderable" attributes
    Then I verify the currency is AUD on the PDP page
    When I add product to my bag from standard PDP Page
    And I verify the currency is AUD on the Add to bag page
    Then I checkout on add to bag overlay
    And I verify the currency is AUD on the shopping bag page
    When I  click on continue checkout button on shoppping bag page
    And I verify the currency is AUD on the Border Free page


  @dsv_desktop_sev2
  Scenario: Verify Price and corresponding Standard shipping method changed based on free shipping threshold
    Given I visit the web site as a guest user
    When I add a "free_shipping and prod_available" product to my bag that is not "beauty"
    And I continue checkout until I reach the shipping page as an "guest" user
    Then I should see default shipping method as "Standard" and price as "FREE"

