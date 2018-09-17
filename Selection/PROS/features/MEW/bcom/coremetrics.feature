Feature: Verify Attribute 20 and 41 in Product view tag when a PROS product is clicked


  @B-66413 @working @random
  Scenario: Verify attribute 20 and 41
    Given I visit the mobile web site as a guest user in domestic mode
    When I search using mobile website for "dresses"
    And I select a random member product using mobile website
    And I click first recommended product from pdp page using mobile website










