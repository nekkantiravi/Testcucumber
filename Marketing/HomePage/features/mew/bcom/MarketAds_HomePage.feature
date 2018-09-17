Feature: As a mobile customer, I want the ability to view timely promotional messages and fashion content, tap on promotions and fashion content on the mobile home page and link to the location to shop product.

#11581: BM2 :: HP :: Mkg and Merch Ads Display

  @wip_iosdriver @no-steps @domain_mew_discovery @mew_regression
  Scenario: Verify marketing ad if promotion exists
    Given I create a promotion in Astra for mobile for the home page
    When I visit the mobile web home page
    Then I should see marketing ad on the home page

  @manual @domain_mew_discovery @mew_regression
  Scenario: Verify marketing ad if promotion does not exist
    Given no asset is detected in Astra for mobile
    When I visit the mobile web home page
    Then a default marketing image will be shown in its place

  @manual @domain_mew_discovery @mew_regression
  Scenario: Verify scrolling for more then one marketing ads
    Given  I create a promotion in Astra for mobile for the home page
    When I visit the mobile web home page
    Then I should be able to scroll marketing ads
#
  @wip_iosdriver  @no-steps @domain_mew_discovery @mew_regression
  Scenario: Verify maximum number of marketing ads on the page
    Given I create a promotion in Astra for mobile for the home page
    When I visit the mobile web home page
    Then I should see no more then ten unique marketing ads

  @manual @domain_mew_discovery
  Scenario: Verify swiping from the last ad to the first one
    Given I create promotions in Astra for mobile
    When I visit the mobile web home page
    And I am scrolling down to get to the last ad on the page
    Then I will need to swipe up to reach the first ad on the page

  @wip_iosdriver  @no-steps @domain_mew_discovery @mew_regression
  Scenario: Verify the order of ads which are displayed on the page
    Given I create a promotion in Astra for mobile for the home page
    When I visit the mobile web home page
    Then I should see ads in according with given order

#11582: BM2 :: HP :: Mkg and Merch Ads Linking

  @wip_iosdriver  @no-steps @domain_mew_discovery @mew_regression
  Scenario: Verify URL for ad
    Given I create a promotion in Astra for mobile for the home page
    When I visit the mobile web home page
    And I click on marketing ad on the home page
    Then I should be redirected from the home page to the given URL for this ad

  @wip_iosdriver  @no-steps @domain_mew_discovery @mew_regression
  Scenario: Verify ad without URL
    Given I create a promotion in Astra for mobile without URL for the home page
    When I visit the mobile web home page
    And I click on marketing ad without url on the home page
    Then I should still stay on home page
