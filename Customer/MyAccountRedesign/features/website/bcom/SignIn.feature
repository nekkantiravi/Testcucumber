#Author: Prachi Agarwal
#Date Created:  06/20/2017
#Date Signed Off: TBD

Feature: Sign-in to Access My Account

  @upi_157_mcom @upi_158_bcom @artifact_shopapp @priority_high @use_domain_qual
  @s4a_stable @domain_customer @use_regression
  Scenario:Verify cookies for SignedIn user
    Given I visit the web site as a guest user
    When I click on signIn link
    And I capture the UserID
    And I navigate to create profile page
    And I create a new profile
    Then I verify secure user token and cookie values after sign in