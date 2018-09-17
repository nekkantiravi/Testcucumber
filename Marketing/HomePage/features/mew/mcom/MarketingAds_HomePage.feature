Feature: As a mobile customer, I want the ability to view timely promotional messages and fashion content, tap on promotions and fashion content on the mobile home page and link to the location to shop product.

#11476: MM2 :: HP :: Marketing Ads Display

  @domain_marketing @mew_regression
  Scenario: Verify marketing ad if promotion exists
    Given I create a promotion in Astra for mobile for the home page
    When I visit the mobile web home page
    Then I should see marketing ad on the home page

#11478: MM2 :: HP :: Marketing Ads Linking

  @domain_marketing @mew_regression
  Scenario: Verify URL for ad
    Given I create a promotion in Astra for mobile for the home page
    When I visit the mobile web home page
    And I click on marketing ad on the home page
    Then I should be redirected from the home page to the given URL for this ad

  @domain_marketing @mew_regression
  Scenario: Verify ad without URL
    # NOTE: At this point, there don't appear to be any such ads
    Given I create a promotion in Astra for mobile without URL for the home page
    When I visit the mobile web home page
    And I click on marketing ad without url on the home page
    Then I should still stay on home page
