#Author: Discovery QE
#Date Created: 09/27/2017
#Date Signed Off:
#Version One: StoryID: B-92183

Feature: As a user on Bloomingdales.com, I want e-gift cards to portray a price range thumbnail of $10.00 - $1000.00
  in browse / search, so that users do not believe they can only purchase up to $10 before navigating to PDP.

  @artifact_navapp @release_16F @priority_medium @project_SNBC @mode_domestic @domain_discovery @use_regression
  Scenario: BrowsePage - Verify the product prices in e-gift card Category Browse page in DOMESTIC mode
    Given I visit the web site as a guest user
    And I clear existing class variables to avoid data issues
    When I navigate to the "E-Gift Cards" browse page under "GIFTS"
    Then I verify product price range for e-gift cards on category browse page as below:
      | egift_card_price_range | $10.00 - $1,000.00 |