Feature: As a customer, I want to see commas in prices on browse/search grids on MEW

  @Mew_UFT @release_17ZA @domain_discovery @project_UFT @domain_mew_discovery @use_mew_regression
  Scenario: verify that the commas in prices on search grids on search result page
    Given I visit the mobile web site as a guest user
    And I search using mobile website for "Diamond rings"
    Then I should see prices of the products has commas with at least 4 digits 

  @Mew_UFT @release_17ZA @domain_discovery @project_UFT @domain_mew_discovery @use_mew_regression
  Scenario: verify that the commas in prices on browse grids on browse result page
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | Jewelry & Accessories |
      | Fine Jewelry          |
	  | Necklaces             |
    Then I should see prices of the products has commas with at least 4 digits
