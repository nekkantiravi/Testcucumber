# Author: Vamshi Koormachalam
# Date Created: February 24th, 2013
# Date Signed Off: TBD

Feature: To verify create profile scenarios


  @upi_97_mcom @artifact_shopapp @priority_medium @use_regression @myaccount_6 @s4a_stable @domain_customer
  Scenario:Verify the Profile is saved when Yes is selected on Save Updates Overlay in My Profile page
    Given I visit the web site as a guest user
    When I create a new profile
    And I update the first_name and last_name in My Profile Page
    When I navigate to address book page from update profile page
    Then I should see the Save Updates Overlay in My Profile page
    When I select "Yes" in Save Updates Overlay on my profile page
    And I verify my profile is updated

  @upi_97_mcom @artifact_shopapp @priority_medium @use_regression @myaccount_6 @s4a_stable @domain_customer
  Scenario:Verify the Profile is not saved when No is selected on Save Updates Overlay in My Profile page
    Given I visit the web site as a guest user
    When I create a new profile
    And I update the first_name and last_name in My Profile Page
    When I navigate to address book page from update profile page
    Then I should see the Save Updates Overlay in My Profile page
    When I select "No" in Save Updates Overlay on my profile page
    Then I should be redirected to "my_address_book" page
    And I should see the profile is not updated


  @upi_97_mcom @artifact_shopapp @priority_medium @use_regression @myaccount_6 @s4a_stable @domain_customer
  Scenario:Verify Save Updates Overlay can be closed on My Profile page
    Given I visit the web site as a guest user
    When I create a new profile
    And I update the first_name and last_name in My Profile Page
    When I navigate to address book page from update profile page
    Then I should see the Save Updates Overlay in My Profile page
    When I select "Close" in Save Updates Overlay on my profile page
    Then I should be redirected to "my_address_book" page
    And I should see the profile is not updated


  @use_regression @artifact_shopapp @artifact_shopapp_2 @myaccount_6 @prod_myaccount @s4a_stable @domain_customer
  Scenario:My Profile - User edits profile but does not submit - Save updates overlay
    Given I visit the web site as a registered user
    When I navigate to my profile page
    And I edit "first_name" field in My Profile Page
    When I navigate to address book page from update profile page
    Then I should see the Save Updates Overlay in My Profile page
    When I select "No" in Save Updates Overlay on my profile page
    Then I should be redirected to "my_address_book" page

  @priority_medium @prod @artifact_shopapp @uft @use_regression @artifact_shopapp_2 @myaccount_6  @s4a_stable @domain_customer
  Scenario:Verify new copy of updated text, manage credit account link, mailing address labels on my profile page
    Given I visit the web site as a registered user
    When I navigate to my profile page
    Then I should see text "Keep your mailing address below up to date to receive exclusive Macy's offers & promotions by mail." on my profile page
    #And I should see informative text "Also need to update the billing address on your Macy's Card?" above to Manage Credit Card links
    And I should see label Mailing Address line 1 on my profile page
    And I should see label Mailing Address line 2 on my profile page

  # @14D
  @priority_medium @prod @artifact_shopapp @uft @use_regression @artifact_shopapp_2 @myaccount_6  @s4a_stable @domain_customer
  Scenario:Verify update billing address pop up window is not displayed when no prop/cobrand card added to profile
    Given I visit the web site as a guest user
    When I create a new profile
    And I update "address_city" on my profile
    Then I should not see update billing address pop up window
    And I verify my profile

  # @14D
  @priority_medium @prod @artifact_shopapp @uft @use_regression @artifact_shopapp_2 @myaccount_6  @s4a_stable @domain_customer @use_browser
  Scenario:Verify update billing address pop up window is not displayed when user updates info other than state, zipcode, city, mailing address and no prop/amex attached to profile
    Given I visit the web site as a guest user
    When I create a new profile
    And I update "first_name" on my profile
    Then I should not see update billing address pop up window
    And I verify my profile

  @upi_96_mcom @artifact_shopapp @priority_medium @use_regression @myaccount_4 @s4a_stable @domain_customer
  Scenario:Verify Receive Emails & Text Me values in DB on Create Profile with default data
    Given I visit the web site as a guest user
    When I create a new profile
    Then I verify my preference receive emails "Thisit Email Newsletter" value should be displayed as "Y" in DB
    And I verify my preference text me "Mobile Phone Marketing Preference" value should be displayed as "Y" in DB
    When I navigate to My Preferences page
    Then I verify send me macys emails preference as "Enabled" and text me preference as "Enabled" in Notification Preferences section

  @upi_96_mcom @artifact_shopapp @priority_medium @use_regression @myaccount_4 @s4a_stable @domain_customer
  Scenario:Verify Receive Emails & Text Me values in DB on Create Profile when Receive Emails unselected & Text Me options selected
    Given I visit the web site as a guest user
    When I create a profile by deselecting receive me emails and selecting text me options
    Then I verify my preference receive emails "Thisit Email Newsletter" value should be displayed as "N" in DB
    And I verify my preference text me "Mobile Phone Marketing Preference" value should be displayed as "Y" in DB
    When I navigate to My Preferences page
    Then I verify send me macys emails preference as "Disabled" and text me preference as "Enabled" in Notification Preferences section

  @upi_96_mcom @artifact_shopapp @priority_medium @use_regression  @myaccount_4 @s4a_stable @domain_customer
  Scenario:Verify Receive Emails & Text Me values in DB on Create Profile when Receive Emails & Text Me options are deselected
    Given I visit the web site as a guest user
    When I create a profile by de-selecting receive me emails and text me options
    Then I verify my preference receive emails "Thisit Email Newsletter" value should be displayed as "N" in DB
    And I verify my preference text me "Mobile Phone Marketing Preference" value should be displayed as "N" in DB
    When I navigate to My Preferences page
    Then I verify send me macys emails preference as "Disabled" and text me preference as "Disabled" in Notification Preferences section

    #MCOM-69987
  @sstbacklog @use_regression @myaccount_6 @domain_customer @saucelab_M @saucelab_M_F1 @run_this
  Scenario: Verify Database when user Create Profile While Creating Registry
    Given I visit the web site as a registry user
    And I verify create profile details in DB