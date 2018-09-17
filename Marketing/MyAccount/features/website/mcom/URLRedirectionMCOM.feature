# Author: Malinka Senevirathne
# Date Created: 07/01/2014
# Date Signed Off:

Feature: URL Redirection

  # MCOM-71404
  @sstbacklog @myaccount_2 @domain_marketing
  Scenario: Verify general URL re-direction rule for category browse page
    Given I visit the web site as a guest user
    And I create a profile by enabling the text me radio with valid phone number
    And I leave the site and return later
    When I navigate to category browse page
    And I navigate a to sign in page using goto_sign_in link
    And I sign in using existing username and password
    Then I should be navigated to previously visited browse page
    # Notes: I opt for SMS option
    # Should be redirected to the previously visited browse page after sign in

  # MCOM-69984
  @sstbacklog @myaccount_1 @domain_marketing
  Scenario Outline: Verify URLs in My Account pages
    Given I visit the web site as a registered user with no CC
    When I navigate to the My Account Page
    Then I should be navigated to "<URL>" for "<My_Account_links>" page
  Examples:
    | My_Account_links             |  URL                                 |
    | My Profile                   |  /account/profile                    |
    | My AddressBook               |  /account/addressbook                |
    | My Wallet                    |  /account/wallet?ocwallet=true       |
    # Notes
    # URL need to be verified according to the loaded page

  # As of 17Q release, below scenario is obsolete as we dont see Zip and State on create profile screen
  # MCOM-69984
#  @use_regression @sstbacklog @myaccount_4 @domain_marketing
#  Scenario: Verify state and zip code fields are displayed in all profile creation page
#    Given I visit the web site as a guest user
#    When I navigate to create profile page
#    Then I should see Zip and State fields in Create Profile Page
#    And I should not see Loyalty ID field in Create Profile Page