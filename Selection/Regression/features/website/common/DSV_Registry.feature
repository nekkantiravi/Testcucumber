Feature: Find Registry

   @dsv_desktop_sev2
  Scenario: As a user I should be able to search and view a registry for dsv
    Given I visit the web site as a guest user
    When I navigate to registry home page
    Then I search for the existing registry
    And I should see GVR items present in the page