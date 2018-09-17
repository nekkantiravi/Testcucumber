#Author: UFT team
#Date Created: 02/27/2017
#Date Signed Off:
#Version One: B-57626

Feature: As a product owner, I would like to ensure that customer's hashed email is present in brightTag object in field hE and hE2 after Sign-in from Wallet.

  @artifact_navapp @domain_customer_management @release_17I @mode_domestic @project_UFT
  Scenario: Verify that brightTag object has hE and hE2 fields when user signed in from My Wallet link under My Account
    Given I visit the web site as a registered user
    When I sign out from my current profile
    And I signin with existing profile from my account dropdown to navigate to "my_wallet" page
    Then I should see hashed email address hE and hE2 tag in bright tag