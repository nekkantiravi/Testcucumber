Feature: Shopping bag page dsv


  @dsv_mew_sev2  @domain_selection
  Scenario: Verify added product details on shopping bag page for MCOM
    Given I visit the mobile web site as a guest user
    When I search using mobile website for "jeans"
    And I select a random member product using mobile website
    Then I should see Add to Bag button on PDP page
    When I select available color and size
    And I select quantity of product items
    And I store product details information
    And I tap on add to bag button
    Then I should see Add To Bag modal
    And I verify pdp product values on add to bag modal

  @dsv_mew_sev2  @domain_selection
  Scenario: As a mobile user, I should see product detail button to navigate away from the page
    Given I visit the mobile web site as a guest user
    When I search using mobile website for "jeans"
    And I select a random member product using mobile website
    Then I should see Add to Bag button on PDP page
    Then I click on image of product and zoom in
    And I click on image of product and zoom in
    And I click on zoom out button
    Then I verify see Add to Bag button on PDP page