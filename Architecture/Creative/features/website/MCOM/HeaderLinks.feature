Feature: Validate header link

  @creative
  Scenario: verify header links
    Given I visit the web site as a guest user
    And I click on "stores" link in the header
    Then I validate the landing page

  @creative
  Scenario: verify header links
    Given I visit the web site as a guest user
    And I click on "Deals" link in the header
    Then I validate the landing page

