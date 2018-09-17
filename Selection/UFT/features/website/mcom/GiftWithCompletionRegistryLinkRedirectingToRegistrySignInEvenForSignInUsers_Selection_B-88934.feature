#Author: UFT team
#Date Created: 08/28/2017
#Date Signed Off:
#Version One:B-88934

Feature: As a product owner, I would like to ensure that once users are signed into their registry
  as they navigate through their registry, they are not being redirected to another sign in page.

  @artifact_customer @mode_domestic @release_17Q @priority_medium @domain_selection @project_UFT
  Scenario: Verify that signed in user is navigated to registry manager page when he clicks on Gift With Completion link
    Given I visit the web site as a registry user
    When I navigate to gifts with completion page using left nav link
    And I select Access Your Registry link on gifts with completion page
    Then I should be navigated to registry manager page


  @artifact_customer @mode_domestic @release_17Q @priority_medium @domain_selection @project_UFT
  Scenario: Verify that signed in user is navigated to registry manager page when he clicks on Access your registry on registry benefits page
    Given I visit the web site as a registry user
    When I mouse over "WEDDING REGISTRY" category from top navigation
    And I select "Registry Benefits" subcategory from flyout menu
    And I select Access Your Registry link on registry benefits page
    Then I should be navigated to registry manager page