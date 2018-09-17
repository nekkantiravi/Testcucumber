#Author: UFT team
#Date Created: 06/17/2015
#Date Signed Off:
#Version One: B-51349 (B-45016)

Feature: As a user on Bloomingdales.com, I want e-gift cards to portray a price range thumbnail of $10.00 - $1000.00
  in browse / search, so that users do not believe they can only purchase up to $10 before navigating to PDP.

  @domain_discovery @mode_domestic @use_regression @migrated_to_sdt @priority_high
  Scenario: CategorySplashPage - E-Gift Card Category Splash Page : Verify the product prices of e-gift card section on Category Splash page in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to the "E-Gift Cards" browse page under "GIFTS"
    Then I verify product price range for e-gift cards on category browse page as below:
      | egift_card_price_range | $10.00 - $1,000.00 |
