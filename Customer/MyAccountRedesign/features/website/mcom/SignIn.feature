#Author: Prachi Agarwal
#Date Created:  06/20/2017
#Date Signed Off: TBD

Feature: Sign-in to Access My Account

  #Mingle: http://mingle/projects/market/cards/157
  #http://mingle/projects/market/cards/158

####################################### Regular Sign In Page #####################################

  @upi_157_mcom @upi_158_bcom @artifact_shopapp @priority_high @use_domain_qual
  @s4a_stable @domain_customer @use_regression
  Scenario:Verify cookies for SignedIn user
    Given I visit the web site as a guest user
    When I click on signIn link
    And I capture the UserID
    And I navigate to create profile page
    And I create a new profile
    Then I verify secure user token and cookie values after sign in


  @artifact_shopapp @mode_domestic @release_17H @priority_low @domain_marketing @project_UFT @use_regression
  Scenario: Verify UI changes on account Sign in page
    Given I visit the web site as a guest user
    When I navigate to the sign-in page
    Then I verify UI changes on account sign in page