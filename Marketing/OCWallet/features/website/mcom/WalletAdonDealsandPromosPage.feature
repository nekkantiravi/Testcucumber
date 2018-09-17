#Author: UFT team
#Date Created: 12/29/2016
#Updated: 1/13/2017
#Date Signed Off:
#Version One: B-67614

Feature: As a product owner, I would like to update the Wall Ad on Deals and promo page

  @artifact_navapp @mode_domestic @release_17A @priority_medium @domain_marketing @project_UFT @use_regression
  Scenario: Verify the updated text is displayed in Wallet ad on Deals & Promotions page for guest user
    Given I visit the web site as a guest user
    When I navigate to deals and promotions page
    Then I should see 'wallet image', 'my wallet' and 'Find Out More' link on deals and promotions page
    And I should see wallet description "Use wallet to check out faster & get new offers instantly! Just sign in or create an account."
    When I click on the get started in deals and promotions page
    Then I should see Sign In Page
    When I sign in using existing username and password
    Then I should see Wallet Page
    # Note: As part above scenario, verify the page re-directions and wallet details on Deals & Promotion page