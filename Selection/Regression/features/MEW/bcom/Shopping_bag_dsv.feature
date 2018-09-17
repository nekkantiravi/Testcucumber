Feature: Shopping bag page dsv

  @dsv_mew_sev2 @domain_selection
  Scenario: Verify added product details on shopping bag page for BCOM
    Given I visit the mobile web site as a guest user
    When I search using mobile website for "jeans"
    And I select a random member product using mobile website
    Then I verify see Add to Bag button on PDP page
    When I click available size of a product
    And I add two quantity of product items
    And I store expected product information
    And I click on add to bag button
    Then I redirect to Add To Bag modal page
    And I verify correct values on add to bag modal

  @dsv_mew_sev2 @domain_selection
  Scenario: As a mobile user, I should see product detail button to navigate away from the page
    Given I visit the mobile web site as a guest user
    When I search using mobile website for "jeans"
    And I select a random member product using mobile website
    Then I verify see Add to Bag button on PDP page
    Then I click on the product image and zoom the image
    Then I verify see Product details is away from page
