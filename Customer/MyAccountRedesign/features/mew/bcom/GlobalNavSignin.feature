#Mobile Lean Lab BCOM
#Created: 11/2/2017
#Author: Sankeerth Kakarna
#Version-one Story: B-95761
Feature: Verify the new sign in link on Global Nav

  @B-95761 @bcom @mew @domain_customer @use_regression 
  Scenario: Verify that sign out link is present after signing in
    Given I visit the mobile web site as a guest user
    When I Sign in using valid credentials by tapping on sign in link
    Then I tap on bloomingdales logo to goto home page
    When I navigate the global navigation menu as follows:
      |Jewelry & Accessories |
      |Fashion Jewelry|
      |Bracelets|
      |Tennis|
    Then I open the global navigation
    Then I verify that sign out link is present on GN
    And I tap on the GN “Sign out” link
    Then I verify that GN closes and I remain on the Tennis browse page

  @B-95761 @bcom @mew @domain_customer @use_regression
  Scenario: Verify that user is taken back to homepage after sign out through the footer link
    Given I goto Mobile Home page
    When I Sign in using valid credentials by tapping on sign in link
    Then I verify that I am on my account page
    When I tap on the footer's "Sign out” link
    Then I should be redirected to the HP
    And I verify the GN sign out link is updated to “Sign in”

  @B-95761 @bcom @mew @domain_customer @use_regression
  Scenario: Verify that  sign in link persists on child fobs
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      |Jewelry & Accessories |
      |Fashion Jewelry|
      |Bracelets|
      |Tennis|
    Then I open the global navigation
    And I verify that Sign in link persists on global nav



