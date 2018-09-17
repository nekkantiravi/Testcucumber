Feature: As a user, verify that the user should be able to see maximum quantity of product on PDP page

  @dsv_mew_sev2 @domain_selection
  Scenario: Verify the ability to see maximum available quantity of product items on PDP page
    Given I visit the mobile web site as a guest user
    When I search using mobile website for "jeans"
    Then I should be in Search Landing page using mobile website
    When I select a random member product using mobile website
    Then I should be redirected to PDP page
    Then I should see maximum available quantity of product on pdp
